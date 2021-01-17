package org.springframework.samples.petclinic.web;


import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
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
		cliente.setDireccion("Calle Ave");
		cliente.setCorreo("pablog@gmail.com");
		
		
		given(this.clienteService.findClienteById(1)).willReturn(Optional.of(cliente));
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente);
		given(this.clienteService.findAll()).willReturn(clientes);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEditCliente() throws Exception{
		mockMvc.perform(get("/clientes/new")).andExpect(status().isOk()).andExpect(model().attributeExists("cliente"))
		.andExpect(view().name("clientes/newCliente"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoClientes() throws Exception{
		mockMvc.perform(get("/clientes")).andExpect(status().isOk())
		.andExpect(model().attributeExists("cliente"))
		.andExpect(view().name("clientes/listadoClientes"));
	}
	
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/clientes/save")
						.with(csrf())
						.param("dni", "20099008E")
						.param("nombre", "Jose Carlos")
						.param("apellidos", "Morales Borreguero")
						.param("telefono", "692069178")
						.param("direccion", "Calle Huertas 31")
						.param("correo", "jcmorales2400@gmail.com"))	
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("clientes/listadoClientes"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/clientes/save")
						.with(csrf())
						.param("dni", "20099008E")
						.param("nombre", "")
						.param("apellidos", "Morales Borreguero")
						.param("telefono", "692069178")
						.param("direccion", "Calle Huertas 31")
						.param("correo", "jcmorales2400@gmail.com"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("cliente"))
			.andExpect(model().attributeHasFieldErrors("cliente", "nombre"))
			.andExpect(view().name("clientes/newCliente"));
	}

}
