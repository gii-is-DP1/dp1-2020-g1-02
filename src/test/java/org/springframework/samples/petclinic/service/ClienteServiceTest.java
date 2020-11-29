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
	
	@Test
	public void testCountWithInitialData() {
		int count = clienteService.clienteCount();
		assertEquals(count, 1);
	}
	
	@Test
	public void findAll() {
		clienteService.findAll();
	}
	
	@Test
	public void findClientById() {
		int clientId = 1;
		clienteService.findClienteById(clientId);
	}
	
	@Test
	public void testDeleteClienteById() {
		clienteService.deleteById(1);
		assertEquals(true, clienteService.findClienteById(1).isEmpty());
	}
	
	@Test
	public void testSaveClienteById() {
		clienteService.saveById(1);
		assertEquals(false, clienteService.findClienteById(1).isEmpty());
	}

}
