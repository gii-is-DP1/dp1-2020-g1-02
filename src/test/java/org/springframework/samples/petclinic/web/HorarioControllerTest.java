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
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= HorarioController.class,
includeFilters= @ComponentScan.Filter(value = Horario.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class HorarioControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private HorarioService horarioService;
	
	@MockBean
	private TrabajadorService trabajadorService;
	
	@MockBean
	private UserService userService;
	
	private Horario horario;
	
	@BeforeEach
	void setup() {
		horario = new Horario();
		horario.setId(1);
		horario.setHora_inicio(LocalDateTime.of(2020, 12, 11, 10, 00));
		horario.setHora_fin(LocalDateTime.of(2020, 12, 11, 15, 00));
		horario.setDescripcion("Ese es tu horario de hoy");
		
		given(this.horarioService.findHorarioById(1)).willReturn(Optional.of(horario));
		
		List<Horario> horarios = new ArrayList<Horario>();
		horarios.add(horario);
		given(this.horarioService.findAll()).willReturn(horarios);
	}

	@WithMockUser(value = "spring")
	@Test
	void testEditHorario() throws Exception{
		mockMvc.perform(get("/horarios/new")).andExpect(status().isOk()).andExpect(model().attributeExists("horario"))
		.andExpect(view().name("horarios/newHorario"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoHorarios() throws Exception{
		mockMvc.perform(get("/horarios")).andExpect(status().isOk())
		.andExpect(model().attributeExists("horarios"))
		.andExpect(view().name("horarios/listadoHorarios"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/horarios/save")
						.with(csrf())
						.param("hora_inicio", "2018/10/03 14:30")
						.param("hora_fin", "2018/10/03 17:30")
						.param("descripcion", "Ese es tu horario de hoy"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/horarios"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/horarios/save")
						.with(csrf())
						.param("hora_inicio", "")
						.param("hora_fin", "2018/10/03 17:30")
						.param("descripcion", "Ese es tu horario de hoy"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("horario"))
			.andExpect(model().attributeHasFieldErrors("horario", "hora_inicio"))
			.andExpect(view().name("horarios/newHorario"));
	}
}
