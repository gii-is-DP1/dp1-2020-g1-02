package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Iterator;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.repository.FacturaRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class FacturaServiceTest {
	
	@Autowired
	private FacturaService facturaService;
	
	@Test
	public void testExistenFacturas() {
		int count = facturaService.facturaCount();
		assertNotEquals(0, count);
	}
	
	@Test
	public void testFindFacturaById() {
		Factura facturaFind = facturaService.findFacturaById(1).get();
		assertEquals(Factura.class, facturaFind.getClass());
	}
	
	@Test
	public void testFindAllFacturasByProveedorId() {
		Integer i = 0;
		Iterable<Factura> facturaFind = facturaService.findFacturaByProveedorId(1);
		Iterator<Factura> iterador = facturaFind.iterator();
		while(iterador.hasNext()) i++;
//		assertTrue(facturaFind.forEach(x-> x.);
	}
	
	@Test
	public void testDeleteFacturaById() {
		facturaService.deleteById(1);
		assertEquals(true, facturaService.findFacturaById(1).isEmpty());
	}
	
	
}
