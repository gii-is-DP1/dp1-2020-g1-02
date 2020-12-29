package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ClienteServiceTest {
	
	@Autowired
	private ClienteService clienteService;
	
	//Tests positivos
	
	@Test
	public void testExistenClientes() {
		int count = clienteService.clienteCount();
		assertEquals(count, 1);
	}
	
	@Test
	public void testfindAll() {
		clienteService.findAll();
	}
	
	@Test
	public void testfindClientById() {
		int clientId = 1;
		Optional<Cliente> cliente = clienteService.findClienteById(clientId);
		assertNotNull(cliente);
	}
	
	@Test
	public void testDeleteClienteById() {
		clienteService.deleteById(1);
		assertEquals(false, clienteService.findClienteById(1).isPresent());
	}
	
	@Test
	public void testSaveCliente() {
		Cliente clienteNew = new Cliente();
		clienteNew.setNombre("José");
		clienteNew.setApellidos("García");
		clienteNew.setDni("47524318G");
		clienteNew.setTelefono("639635963");
		clienteNew.setDireccion("Sevilla");
		clienteNew.setCorreo("joseg@gmail.com");

		clienteService.save(clienteNew);
		
		Integer cantidad = clienteService.clienteCount();
		assertEquals(2, cantidad);
	}

}
