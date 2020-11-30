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
	@Autowired
	private TrabajadorService trabajadorService;
	
	@Test
	public void testCountWithInitialData() {
		int count = contratoService.contratoTrabajadorCount();
		assertEquals(count, 1);
	}
	
	@Test
	public void testInsertarContratoTrabajador() {
		ContratoTrabajador ct= new ContratoTrabajador();
		ct.setFechainicial(LocalDate.of(2021,1, 1));
		ct.setFechafinal(LocalDate.of(2021, 12, 31));
		ct.setSueldo(800.0);
		ct.setTrabajador(trabajadorService.findTrabajadorById(1).get());
		
		contratoService.save(ct);
		int count =contratoService.contratoTrabajadorCount();
		assertEquals(count,2);
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
		assertEquals(count, 0);
		
	}
}
