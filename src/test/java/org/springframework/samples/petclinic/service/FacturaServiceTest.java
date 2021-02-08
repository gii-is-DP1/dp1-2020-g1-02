package org.springframework.samples.petclinic.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class FacturaServiceTest {
	
	@Autowired
	private FacturaService facturaService;

	//Test positivos
	
	@Test
	public void testExistenFacturas() {
		int count = facturaService.facturaCount();
		assertNotEquals(0, count);
	}
	
	@Test
	public void testFindFacturaById() {
		Factura facturaFind = facturaService.findFacturaById(2).get();
		assertEquals(137.89, facturaFind.getPrecio_total());
	}
	
//	@Test
//	@Transactional
//	public void testCreaFactura() {
//		Oferta o = ofertaService.findOfertaById(1).get();
//		Pedido p = new Pedido();
//		p.setId(1);
//		p.setFechaPedido(LocalDate.now());
//		p.setCantidadProducto(7);
//		p.setOferta(o);
//		facturaService.creaFactura(p);
//		Double precioTotal = Double.parseDouble(p.getOferta().getPrecioU()) * p.getCantidadProducto();
//		assertEquals(precioTotal, 17.5);
//	}

	@Test
	public void testFindAllFacturasByProveedor() {
		Collection<Factura> facturasIT = facturaService.findFacturaByProveedorName("Lejias");
		assertEquals(1, facturasIT.size());
	}
	
	@Test
	@Transactional
	public void testDeleteFacturaById() {
		facturaService.deleteById(1);
		assertEquals(false, facturaService.findFacturaById(1).isPresent());
	}
	
	@Test
	public void testFindAll() {
		List<Factura> facturas = (List<Factura>) facturaService.findAll();
		assertEquals(facturaService.facturaCount(), facturas.size());
	}
	
	//Test negativos
	
	@Test
	public void testNotFindFacturaById() {
		Optional<Factura> facturaFind = facturaService.findFacturaById(50);
		assertEquals(false, facturaFind.isPresent());
	}
	
	@Test
	public void testNotFindAllFacturasByProveedor() {
		Collection<Factura> facturaFind = facturaService.findFacturaByProveedorName("pepino");
		assertEquals(true, facturaFind.isEmpty());
	}
	
}
