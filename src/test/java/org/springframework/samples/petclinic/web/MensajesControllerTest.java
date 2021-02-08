package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Mensaje;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.MensajesService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= MensajesController.class,
includeFilters= @ComponentScan.Filter(value = MensajesValidator.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class MensajesControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MensajesService mensajesService;
	@MockBean
	private UserService userService;
	@MockBean
	private EntityManager entityManager;
	
	private Mensaje mensaje1;
	private Mensaje mensaje2;
	private User user1;
	private User user2;
	private Authorities auth1;
	private Authorities auth2;
	
	@BeforeEach
	void setup() {
		mensaje1 = new Mensaje();
		mensaje2 = new Mensaje();
		user1 = new User();
		user2 = new User();
		auth1 = new Authorities();
		auth2 = new Authorities();
		
		mensaje1.setId(1);
		mensaje1.setAsunto("Prueba");
		mensaje1.setCuerpo("Esto es una prueba");
		mensaje1.setFecha(LocalDate.now());
		mensaje1.setLeido(false);
		
		mensaje2.setId(1);
		mensaje2.setAsunto("Prueba2");
		mensaje2.setCuerpo("Esto es una prueba2");
		mensaje2.setFecha(LocalDate.now());
		mensaje2.setLeido(false);
		
		user1.setUsername("prueba1");
		user1.setPassword("prueba1");
		user2.setUsername("prueba2");
		user2.setPassword("prueba2");
		
		auth1.setId(1);
		auth1.setAuthority("cliente");
		auth1.setUser(user1);
		auth2.setId(2);
		auth2.setAuthority("proveedor");
		auth2.setUser(user2);
		
		given(this.entityManager.find(User.class, "prueba1")).willReturn(user1);
		given(this.entityManager.find(User.class, "prueba2")).willReturn(user2);
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoMensajes() throws Exception {
		mockMvc.perform(get("/mensajes")).andExpect(status().isOk())
		.andExpect(model().attributeExists("mensajes"))
		.andExpect(view().name("mensajes/bandejaEntrada"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNewPedido() throws Exception {
		mockMvc.perform(get("/mensajes/new"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("users", "size", "mensaje"))
		.andExpect(view().name("mensajes/newMensaje"));
	}
	
//	@WithMockUser(value = "spring")
//    @Test
//    void testSalvarMensaje() throws Exception {
//		mockMvc.perform(post("/mensajes/save")
//						.with(csrf())
//						.param("receptores", "prueba2")
//						.param("emisor", "prueba1")
//						.param("fecha", "2021/02/08")
//						.param("asunto", "Prueba")
//						.param("cuerpo", "esto es una prueba")
//						.param("leido", "false")
//						)
//			.andExpect(status().isOk())
//			.andExpect(view().name("redirect:/mensajes"));
//	}

}
