package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.ContratoTrabajador;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ContratoTrabajadorServiceTest {
	
	@Autowired
	private ContratoTrabajadorService contratoService;
	
	@Test
	public void testCountWithInitialData() {
		int count = contratoService.contratoTrabajadorCount();
		assertEquals(count, 3);
	}
	
	@Test
	public void testInsertarContratoTrabajador() {
		ContratoTrabajador ct= new ContratoTrabajador();
		ct.setFechainicial(LocalDate.now());
		ct.setFechafinal(LocalDate.of(2050, 12, 31));
		ct.setSueldo(800.0);
		
		contratoService.save(ct);
		int count =contratoService.contratoTrabajadorCount();
		assertEquals(count,4);
	}
	
	@Test
	public void testGetContratoTrabajadorById() {
		assertNotNull(contratoService.findContratoTrabajadorById(1).get());
	}
	
	@Test
	public void testGetContratoTrabajadorByIdNotFound() {
		assertThrows(NoSuchElementException.class, () -> {contratoService.findContratoTrabajadorById(100).get();});
	}
	
	@Test
	public void testDeleteContratoTrabajador() {
		ContratoTrabajador contrato = contratoService.findContratoTrabajadorById(1).get();
		contratoService.delete(contrato);
		int count = contratoService.contratoTrabajadorCount();
		//Assert
		assertEquals(count, 2);
		
	}
}
