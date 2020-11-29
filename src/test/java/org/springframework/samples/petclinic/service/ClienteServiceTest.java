package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
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
	private Cliente cliente;
	
	@Test
	public void testCountWithInitialData() {
		int count = clienteService.clienteCount();
		assertEquals(count, 1);
	}
	
	@Test
	public void findAll() {
		Iterable<Cliente> cliente = clienteService.findAll();
	}
	
	@Test
	public void findClientById(int clienteId) {
		Optional<Cliente> c = clienteService.findClienteById(clienteId);
	}
	
//	@Test
//	public void delete(Cliente cliente) {
//		clienteService.delete(cliente);
//	}

}
