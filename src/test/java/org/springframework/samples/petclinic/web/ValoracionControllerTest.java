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
import org.springframework.samples.petclinic.model.Instalacion;
import org.springframework.samples.petclinic.model.NivelSatisfaccion;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Valoracion;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.ValoracionService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= ValoracionController.class,
//includeFilters= @ComponentScan.Filter(value = ValoracionValidator.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class ValoracionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ValoracionService valoracionService;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private ServicioService servicioService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private EntityManager entityManager;
	
	private Valoracion valoracion;
	private User user;
	private Authorities authority;
	private Cliente cliente;
	private Servicio servicio;
	
	@BeforeEach
	void setup() {
		//Preparacion de la valoración
		valoracion = new Valoracion();
		valoracion.setId(1);
		valoracion.setFecha(LocalDate.of(2019, 05, 15));
		valoracion.setValoracion(4);
		
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
		cliente.setUser(user);
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
		
		given(this.valoracionService.findValoracionById(1)).willReturn(Optional.of(valoracion));
		given(this.clienteService.findClienteById(1)).willReturn(Optional.of(cliente));
		given(this.entityManager.find(Valoracion.class, 1)).willReturn(valoracion);
		given(this.entityManager.find(Cliente.class, 1)).willReturn(cliente);
		given(this.servicioService.findServicioById(1)).willReturn(Optional.of(servicio));
		given(this.clienteService.findClienteByUsername(any())).willReturn(Optional.of(cliente));
		given(this.userService.getLoggedUser()).willReturn(user);
		
		List<Valoracion> valoraciones = new ArrayList<Valoracion>();
		valoraciones.add(valoracion);
		given(this.valoracionService.findAll()).willReturn(valoraciones);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoValoraciones() throws Exception{
		mockMvc.perform(get("/valoraciones")).andExpect(status().isOk())
		.andExpect(model().attributeExists("valoraciones"))
		.andExpect(view().name("valoraciones/listadoValoraciones"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNewValoracion() throws Exception{
		mockMvc.perform(get("/valoraciones/new/{oId}",1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("valoracion"))
		.andExpect(model().attribute("valoracion", hasProperty("servicio", is(servicio))))
		.andExpect(view().name("valoraciones/newValoracion"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSaveValoracion() throws Exception {
		mockMvc.perform(post("/valoraciones/save")
						.with(csrf())
						.param("fecha", "2020/04/09")
						.param("valor", "4")
						.param("cliente", "1")
						.param("servicio", "1"))	
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("succesful"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSaveValoracionErrors() throws Exception {
		mockMvc.perform(post("/valoraciones/save")
						.with(csrf())
						.param("fecha", "")
						.param("nivel_satisfaccion", "4"))	
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("valoracion"))
			.andExpect(model().attributeHasFieldErrors("valoracion", "fecha"))
			.andExpect(view().name("valoraciones/newValoracion"));
	}

}
