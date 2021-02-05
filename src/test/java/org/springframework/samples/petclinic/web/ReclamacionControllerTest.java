package org.springframework.samples.petclinic.web;


import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hamcrest.beans.HasProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.RegistroHoras;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= ReclamacionController.class,
includeFilters= @ComponentScan.Filter(value = Reclamacion.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class ReclamacionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ReclamacionService reclamacionService;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private ServicioService servicioService;
	
	@MockBean
	private EntityManager entityManager;
	
	@MockBean
	private UserService userService;
	
	private Reclamacion reclamacion;
	private Cliente cliente;
	private User user;
	private Authorities authority;
	private Servicio servicio;
	
	@BeforeEach
	void setup() {
		reclamacion = new Reclamacion();
		reclamacion.setId(1);
		reclamacion.setFecha(LocalDate.of(2020, 11, 11));
		reclamacion.setDescripcion("Trabajo incompleto");
		
		authority = new Authorities();
		user = new User();
		authority.setAuthority("cliente");
		authority.setId(1);
		user.setUsername("JoseCarlos");
		authority.setUser(user);
		user.setAuthorities(authority);
		user.setPassword("admin");
		
		cliente = new Cliente();
		cliente.setId(1);
		cliente.setDni("53985965D");
		cliente.setNombre("Pablo");
		cliente.setApellidos("Gonzalez");
		cliente.setTelefono("633444555");
		cliente.setDireccion("Calle Ave");
		cliente.setCorreo("pablog@gmail.com");
		
		servicio = new Servicio();
		servicio.setId(1);
		servicio.setLugar("Acuario de Sevilla");
		servicio.setTipocategoria(TipoCategoria.Cristaleria);
		servicio.setEstado(EstadoServicio.Aceptado);
		servicio.setFechainicio(LocalDate.of(2020, 12, 31));
		servicio.setFechafin(LocalDate.of(2021, 01, 12));
		
		given(this.reclamacionService.findReclamacionById(1)).willReturn(Optional.of(reclamacion));
		given(this.clienteService.findClienteById(1)).willReturn(Optional.of(cliente));
		given(this.entityManager.find(Reclamacion.class, 1)).willReturn(reclamacion);
		given(this.entityManager.find(Cliente.class, 1)).willReturn(cliente);
		given(this.servicioService.findServicioById(1)).willReturn(Optional.of(servicio));
		
		given(this.clienteService.findClienteByUsername(any())).willReturn(Optional.of(cliente));
		given(this.userService.getLoggedUser()).willReturn(user);
		
		List<Reclamacion> reclamaciones = new ArrayList<Reclamacion>();
		reclamaciones.add(reclamacion);
		given(this.reclamacionService.findAll()).willReturn(reclamaciones);
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoReclamaciones() throws Exception{
		mockMvc.perform(get("/reclamaciones")).andExpect(status().isOk())
		.andExpect(model().attributeExists("reclamaciones"))
		.andExpect(view().name("reclamaciones/listadoReclamaciones"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNewReclamacion() throws Exception{
		mockMvc.perform(get("/reclamaciones/new/{oId}",1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("reclamacion"))
		.andExpect(model().attribute("reclamacion", hasProperty("servicio", is(servicio))))
		.andExpect(view().name("reclamaciones/newReclamacion"));
	}
	
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/reclamaciones/save")
						.with(csrf())
						.param("fecha", "LocalDate.of(2020, 08, 07)")
						.param("descripcion", "El operario no estaba"))	
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("reclamaciones/newReclamacion"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/reclamaciones/save")
						.with(csrf())
						.param("fecha", "LocalDate.of(2020, 08, 07)")
						.param("descripcion", ""))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("reclamacion"))
			.andExpect(model().attributeHasFieldErrors("reclamacion", "descripcion"))
			.andExpect(view().name("reclamaciones/newReclamacion"));
	}
}
