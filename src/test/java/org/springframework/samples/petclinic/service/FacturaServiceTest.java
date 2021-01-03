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
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class FacturaServiceTest {
	
	@Autowired
	private FacturaService facturaService;
	@Autowired
	private ProveedorService proveedorService;
	@Autowired
	private PedidoService pedidoService;
	
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
	@Transactional
	public void testSaveFactura() {
		Factura facturaNew = new Factura();
		facturaNew.setFecha(LocalDate.now());
		facturaNew.setPrecio_total(50.0);
		facturaNew.setProveedor(proveedorService.findProveedorById(2).get());
//		
		Pedido ped = new Pedido();
		ped.setFechaPedido(LocalDate.now());
		pedidoService.save(ped);
		facturaNew.setPedido(ped);;

		facturaService.save(facturaNew);
		
		Integer cantidad = facturaService.facturaCount();
		
		assertEquals(4, cantidad);
	}
	
	

	@Test
	public void testFindAllFacturasByProveedor() {
		Integer cant= 0;
		Iterator<Factura> facturasIT = facturaService.findFacturaByProveedorName("Lejias").iterator();
		while(facturasIT.hasNext()) {
			cant++;
		}
		assertEquals(2, cant);
	}
	
	@Test
	public void testFindAllFacturasByProveedor() {
		Integer cant= 0;
		Iterator<Factura> facturasIT = facturaService.findFacturaByProveedorName("Lejias").iterator();
		while(facturasIT.hasNext()) {
			cant++;
		}
		assertEquals(2, cant);
	}
	
	@Test
	@Transactional
	public void deleteFacturaById() {
		facturaService.deleteById(1);
		assertEquals(false, facturaService.findFacturaById(1).isPresent());
	}
	
	//Test negativos
	
	@Test
	public void testNotFindFacturaById() {
		Optional<Factura> facturaFind = facturaService.findFacturaById(50);
		assertEquals(false, facturaFind.isPresent());
	}
	
//	@Test
//	public void testNotFindAllFacturasByProveedor() {
//		Iterable<Factura> facturaFind = facturaService.findFacturaByProveedorId(50);
//		Iterator<Factura> iterador = facturaFind.iterator();
//		int cont = 0;
//		while(iterador.hasNext()) cont++;
//		assertEquals(0, cont);
//	}
	
}
