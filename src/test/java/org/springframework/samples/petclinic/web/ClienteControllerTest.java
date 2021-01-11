package org.springframework.samples.petclinic.web;


import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= ClienteController.class,
			includeFilters= @ComponentScan.Filter(value = ClienteValidator.class, type = FilterType.ASSIGNABLE_TYPE ),
			excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
			excludeAutoConfiguration= SecurityConfiguration.class)

public class ClienteControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClienteService clienteService;
	
	private Cliente cliente;
	
	@BeforeEach
	void setup() {
		//Preparacion del cliente
		cliente = new Cliente();
		cliente.setId(1);
		cliente.setDni("53985965D");
		cliente.setNombre("Pablo");
		cliente.setApellidos("Gonzalez");
		cliente.setTelefono("633444555");
		cliente.setCorreo("pablog@gmail.com");
		
//		cliente.setInstalaciones();
		
		given(this.clienteService.findClienteById(1)).willReturn(Optional.of(cliente));
		
		List<Cliente> clients = new ArrayList<Cliente>();
		clients.add(cliente);
		given(this.clienteService.findAll()).willReturn(clients);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEditCliente() throws Exception{
		mockMvc.perform(get("/clientes/new")).andExpect(status().isOk()).andExpect(model().attributeExists("cliente"))
		.andExpect(view().name("clientes/newCliente"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateslotgameForm() throws Exception {
		mockMvc.perform(get("/delete/{clientId}", 1)).andExpect(status().isOk())
				.andExpect(model().attributeExists("client"))
				.andExpect(model().attribute("cliente", hasProperty("nombre", is("Pablo"))))
				.andExpect(model().attribute("cliente", hasProperty("telefono", is("633444555"))))
				.andExpect(model().attribute("cliente", hasProperty("dni", is("53985965D"))))
				.andExpect(model().attribute("cliente", hasProperty("apellidos", is("Gonzalez"))))
				.andExpect(model().attribute("cliente", hasProperty("correo", is("pablog@gmail.com"))))
				.andExpect(view().name("clientes/listadoClientes"));
	}
	
//	@WithMockUser(value = "spring")
//    @Test
//    void testAñadirCliente() throws Exception {
//		mockMvc.perform(post("/clientes/save").param("cliente", "Fer")
//						.param("apellidos", "Valdes")
//						.param("telefono", "643213430")
//						.param("dni", "32324586J"))
//			.andExpect(status().is2xxSuccessful())
//			.andExpect(view().name("clientes/listadoClientes"));
//	}
}
