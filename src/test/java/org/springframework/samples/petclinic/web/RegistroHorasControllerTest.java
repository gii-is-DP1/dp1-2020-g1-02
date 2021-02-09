package org.springframework.samples.petclinic.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.assertj.core.internal.Iterables;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.util.Pair;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.RegistroHoras;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.RegistroHorasService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.samples.petclinic.service.UserService;
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
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private EntityManager entityManager;
	
	
	private RegistroHoras registroHora;
	private User user;
	private Authorities authority;
	private Trabajador trabajador;
	
	@BeforeEach
	void setup() {
		registroHora = new RegistroHoras();
		registroHora.setId(1);
		registroHora.setFecha(LocalDate.of(2020, 1, 1));
		registroHora.setHora_inicio(LocalTime.of(12, 30));
		registroHora.setHora_fin(LocalTime.of(17,30));
		registroHora.setTrabajador(trabajador);
		
		authority = new Authorities();
		user = new User();
		authority.setAuthority("trabajador");
		authority.setId(1);
		user.setUsername("JoseCarlos");
		authority.setUser(user);
		user.setAuthorities(authority);
		user.setPassword("admin");
		
		trabajador = new Trabajador();
		trabajador.setId(1);
		trabajador.setNombre("Jose");
		trabajador.setApellidos("Morales");
		trabajador.setTelefono("666333222");
		trabajador.setDireccion("calle huertas, n12");
		trabajador.setCorreo("jc@gmail.com");
		trabajador.setDni("20099008W");
		trabajador.setTipocategoria(TipoCategoria.Cristaleria);
		
		
		given(this.registroHorasService.findRegistroHorasById(1)).willReturn(Optional.of(registroHora));
		given(this.registroHorasService.findRegistroHorasByTrabajadorId(any())).willReturn(Pair.of(5.0, Lists.list(registroHora)));
		given(this.trabajadorService.findTrabajadorById(1)).willReturn(Optional.of(trabajador));
		given(this.entityManager.find(RegistroHoras.class, 1)).willReturn(registroHora);
		given(this.entityManager.find(Trabajador.class, 1)).willReturn(trabajador);
		
		
		
		given(this.trabajadorService.findTrabajadorByUsername(any())).willReturn(Optional.of(trabajador));
		given(this.userService.getLoggedUser()).willReturn(user);
		
		
		List<RegistroHoras> registroHoras = new ArrayList<RegistroHoras>();
		registroHoras.add(registroHora);
		given(this.registroHorasService.findAll()).willReturn(Lists.list(registroHora));
		
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoRegistroHoras() throws Exception{
		mockMvc.perform(get("/registroHoras/{tId}",1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("registrohoras"))
		.andExpect(view().name("registroHoras/listadoRegistroHoras"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNewRegistroHoras() throws Exception{
		mockMvc.perform(get("/registroHoras/new"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("registro_horas", "trabajador"))
		.andExpect(view().name("registroHoras/newRegistroHoras"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void DeleteAdministrador() throws Exception{
		mockMvc.perform(get("/registroHoras/delete/{registroHorasId}", 1))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/registroHoras"));
	}
	
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/registroHoras/save")
						.with(csrf())
						.param("fecha", "2021/08/12")
						.param("hora_inicio", "10:00")
						.param("hora_fin", "15:00")
						.param("trabajador", "1"))	
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("succesful"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/registroHoras/save")
						.with(csrf())
						.param("fecha", "2021/08/12")
						.param("hora_inicio", "")
						.param("hora_fin", "15:00")
						.param("trabajador", "1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("registroHoras"))
			.andExpect(model().attributeHasFieldErrors("registroHoras", "hora_inicio"))
			.andExpect(view().name("registroHoras/newRegistroHoras"));
	}

}