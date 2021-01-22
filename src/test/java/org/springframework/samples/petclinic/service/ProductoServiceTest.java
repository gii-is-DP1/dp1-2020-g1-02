package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ProductoServiceTest {
	
	@Autowired
	private ProductoService productoService;
	
	//Test positivos
	
	@Test
	public void testExistenProductos() {
		int count = productoService.productCount();
		assertEquals(9, count);
	}
	
	@Test
	public void testEncuentraProductoById() {
		Producto productoFind = productoService.findProductoById(1).get();
		assertEquals(Producto.class, productoFind.getClass());
	}
	
	@Test
	public void testGuardarProducto() {
		Producto pGuardar = new Producto();
		pGuardar.setName("fregona");
		pGuardar.setCantidad(30);
		productoService.save(pGuardar);
		int count = productoService.productCount();
		assertEquals(count, 10);
	}
	
	@Test
	public void testBorrarProductoById() {
		productoService.deleteById(1);
		assertEquals(false, productoService.findProductoById(1).isPresent());
	}
	
	@Test
	public void testFindByName() {
		String name = "Escoba";
		assertTrue(productoService.findByName(name).get().getName().equals(name));
		
	}
	//Test negativos
	@Test
	public void testNoEncuentraProductoById() {
		Optional<Producto> productoFind = productoService.findProductoById(50);
		assertNotEquals(true, productoFind.isPresent());
	}

}
