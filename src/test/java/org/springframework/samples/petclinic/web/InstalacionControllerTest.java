package org.springframework.samples.petclinic.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.samples.petclinic.model.Instalacion;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.InstalacionService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= InstalacionController.class,
includeFilters= @ComponentScan.Filter(value = Instalacion.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class InstalacionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private InstalacionService instalacionService;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private EntityManager entityManager;
	
	private Instalacion instalacion;
	private User user;
	private Authorities authority;
	private Cliente cliente;
	
	@BeforeEach
	void setup() {
		instalacion = new Instalacion();
		instalacion.setId(1);
		instalacion.setDimension(200.4);
		instalacion.setLugar("Calle Ave del Paraiso 23");
		
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
		
		
		given(this.instalacionService.findInstalacionById(1)).willReturn(Optional.of(instalacion));
		given(this.clienteService.findClienteById(1)).willReturn(Optional.of(cliente));
		given(this.entityManager.find(Instalacion.class, 1)).willReturn(instalacion);
		given(this.entityManager.find(Cliente.class, 1)).willReturn(cliente);
		
		given(this.clienteService.findClienteByUsername(any())).willReturn(Optional.of(cliente));
		given(this.userService.getLoggedUser()).willReturn(user);
		
		List<Instalacion> instalaciones = new ArrayList<Instalacion>();
		instalaciones.add(instalacion);
		given(this.instalacionService.findAll()).willReturn(instalaciones);
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoInstalaciones() throws Exception{
		mockMvc.perform(get("/instalaciones")).andExpect(status().isOk())
		.andExpect(model().attributeExists("instalacion"))
		.andExpect(view().name("instalaciones/listadoInstalaciones"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testVerInstalacion() throws Exception{
		mockMvc.perform(get("/instalaciones/{instalacionId}",1)).andExpect(status().isOk()).andExpect(model().attributeExists("instalacion"))
		.andExpect(view().name("instalaciones/verInstalacion"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFiltradoInstalaciones() throws Exception{
		mockMvc.perform(get("/instalaciones/filtrado")).andExpect(status().isOk()).andExpect(model().attributeExists("instalaciones"))
		.andExpect(view().name("instalaciones/listadoInstalaciones"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoInstalacionesPorCliente() throws Exception{
		mockMvc.perform(get("/instalaciones/misInstalaciones")).andExpect(status().isOk()).andExpect(model().attributeExists("instalaciones"))
		.andExpect(view().name("instalaciones/listadoInstalaciones"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception{
		mockMvc.perform(get("/instalaciones/new")).andExpect(status().isOk()).andExpect(model().attributeExists("instalacion"))
		.andExpect(view().name("instalaciones/newInstalacion"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/instalaciones/save")
						.with(csrf())
						.param("dimension", "120.3")
						.param("lugar", "Calle Río Danubio 23")
						.param("cliente", "1"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("succesful"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void DeleteInstalacion() throws Exception{
		mockMvc.perform(get("/instalaciones/delete/{instalacionId}", 1))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/instalaciones"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void InstalacionePorCliente() throws Exception{
		mockMvc.perform(get("/instalaciones/misInstalaciones"))
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("instalaciones/listadoInstalaciones"));
	}
	
	
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/instalaciones/save")
						.with(csrf())
						.param("dimension", "")
						.param("lugar", "Calle Santa Maria")
						.param("cliente", "1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("instalacion"))
			.andExpect(model().attributeHasFieldErrors("instalacion", "dimension"))
			.andExpect(view().name("instalaciones/newInstalacion"));
	}

}
