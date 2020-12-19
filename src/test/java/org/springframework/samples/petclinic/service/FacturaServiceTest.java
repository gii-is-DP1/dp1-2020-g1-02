package org.springframework.samples.petclinic.service;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import java.util.Iterator;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class FacturaServiceTest {
	
	@Autowired
	private FacturaService facturaService;
	@Autowired
	private ProveedorService proveedorService;
//	@Autowired
//	private PedidoSerice pedidoService;
	
	//Test positivos
	
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
	public void testSaveFactura() {
		Factura facturaNew = new Factura();
		facturaNew.setFecha(LocalDate.now());
		facturaNew.setPrecio_total(50.0);
		facturaNew.setProveedor(proveedorService.findProveedorById(2).get());
		
		Pedido ped = new Pedido();
		ped.setFechaPedido(LocalDate.now());
		
		facturaNew.setPedido(ped);;

		facturaService.save(facturaNew);
		
		Integer cantidad = facturaService.facturaCount();
		
		assertEquals(4, cantidad);
	}

	
	
//	@Test
//	public void testFindAllFacturasByProveedor() {
//		Boolean i = true;
//		Iterable<Factura> facturaFind = facturaService.findFacturasByProveedorId(1);
//		Iterator<Factura> iterador = facturaFind.iterator();
//		while(iterador.hasNext()) if(iterador.next().getProveedor().getId() != 1) i =false;
//		assertTrue(i);
//	}
	
	@Test
	public void testDeleteFacturaById() {
		facturaService.deleteById(1);
		assertEquals(false, facturaService.findFacturaById(1).isPresent());
	}
	
	//Test negativos
	
	@Test
	public void testNotFindFacturaById() {
		Optional<Factura> facturaFind = facturaService.findFacturaById(50);
		assertEquals(false, facturaFind.isPresent());
	}
	
	@Test
	public void testNotFindAllFacturasByProveedor() {
		Iterable<Factura> facturaFind = facturaService.findFacturasByProveedorId(50);
		Iterator<Factura> iterador = facturaFind.iterator();
		int cont = 0;
		while(iterador.hasNext()) cont++;
		assertEquals(0, cont);
	}
	
}
