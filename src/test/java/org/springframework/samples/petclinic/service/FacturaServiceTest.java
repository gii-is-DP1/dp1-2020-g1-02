package org.springframework.samples.petclinic.service;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import java.util.Iterator;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Factura;
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
	
//	@Test
//	public void testSaveFactura() {
//		Integer cantidadFacturasIni = 0;
//		Iterable<Factura> facturaFind = facturaService.findAll();
//		Iterator<Factura> iterador = facturaFind.iterator();
//		while(iterador.hasNext()) cantidadFacturasIni++;
//		
//		Factura facturaNew = new Factura();
//		facturaNew.setId(10);
//		facturaNew.setId_ped(3);
//		facturaNew.setId_prov(1);
//		facturaNew.setPrecio_total(50.0);
//		facturaNew.setFecha(LocalDate.now());
//		facturaService.save(facturaNew);
//		
//		Integer cantidadFacturasFin = 0;
//		Iterable<Factura> facturaFind2 = facturaService.findAll();
//		Iterator<Factura> iterador2 = facturaFind2.iterator();
//		while(iterador2.hasNext()) cantidadFacturasFin++;
//		
//		assertEquals(cantidadFacturasIni+1, cantidadFacturasFin);
//		
//		
//		Factura facturaEdit = facturaService.findFacturaById(1).get();
//		facturaEdit.setPrecio_total(3.5);
//		facturaEdit.setId_ped(100);;
//		facturaService.save(facturaEdit);
//		assertEquals(3.5, facturaService.findFacturaById(1).get().getPrecio_total());
//	}
	
	
	@Test
	public void testFindAllFacturasByProveedorId() {
		Boolean i = true;
		Iterable<Factura> facturaFind = facturaService.findFacturasByProveedorId(1);
		Iterator<Factura> iterador = facturaFind.iterator();
		while(iterador.hasNext()) if(iterador.next().getId_prov() != 1) i =false;
		assertTrue(i);
	}
	
	@Test
	public void testDeleteFacturaById() {
		facturaService.deleteById(1);
		assertEquals(true, facturaService.findFacturaById(1).isEmpty());
	}
	
	
}
