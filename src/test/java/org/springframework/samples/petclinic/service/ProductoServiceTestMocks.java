package org.springframework.samples.petclinic.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ProductoServiceTestMocks {
	
	@Mock
	private ProductoRepository productRepo;
	
	@Autowired
	private ProductoService productoService;
	
	@BeforeEach
	void setup() {
		
		Producto producto = new Producto();
		producto.setCantidad(10);
		producto.setId(1);
		producto.setName("Escoba");
		
		given(this.productRepo.count()).willReturn((long) 1);
		
	}
	
	@Test
	public void testCountProductos() {
		int count = productoService.productCount();
		assertEquals("1", count);
	}
	

}
