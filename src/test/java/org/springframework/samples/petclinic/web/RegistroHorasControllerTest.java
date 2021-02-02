package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.RegistroHoras;
import org.springframework.samples.petclinic.service.RegistroHorasService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= RegistroHorasController.class,
includeFilters= @ComponentScan.Filter(value = RegistroHoras.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class RegistroHorasControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RegistroHorasService registroHorasService;
	
	@MockBean
	private TrabajadorService trabajadorService;
	
	private RegistroHoras registroHora;
	
	@BeforeEach
	void setup() {
		registroHora = new RegistroHoras();
		registroHora.setId(1);
		registroHora.setHora_entrada(LocalDateTime.of(2020, 12, 10, 8, 00));
		registroHora.setHora_salida(LocalDateTime.of(2020, 12, 10, 15, 00));
		
		given(this.registroHorasService.findRegistroHorasById(1)).willReturn(Optional.of(registroHora));
		
		List<RegistroHoras> registroHoras = new ArrayList<RegistroHoras>();
		registroHoras.add(registroHora);
		given(this.registroHorasService.findAll()).willReturn(registroHoras);
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoRegistroHoras() throws Exception{
		mockMvc.perform(get("/registroHoras")).andExpect(status().isOk())
		.andExpect(model().attributeExists("registro_horas"))
		.andExpect(view().name("registroHoras/listadoRegistroHoras"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNewRegistroHoras() throws Exception{
		mockMvc.perform(get("/registroHoras/new")).andExpect(status().isOk()).andExpect(model().attributeExists("registro_horas"))
		.andExpect(view().name("registroHoras/newRegistroHoras"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/registroHoras/save")
						.with(csrf())
						.param("hora_entrada", "2020/12/10 10:00")
						.param("hora_salida", "2020/12/10 15:00"))	
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("registroHoras/listadoRegistroHoras"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/registroHoras/save")
						.with(csrf())
						.param("hora_entrada", "")
						.param("hora_salida", "2020/12/10 15:00"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("registro_horas"))
			.andExpect(model().attributeHasFieldErrors("registro_horas", "hora_entrada"))
			.andExpect(view().name("registroHoras/newRegistroHoras"));
	}

}
