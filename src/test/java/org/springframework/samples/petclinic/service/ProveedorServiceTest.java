package org.springframework.samples.petclinic.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ProveedorServiceTest {

	@Autowired
	private ProveedorService proveedorService;
	
	//Test positivos
	
	@Test
	public void testExistenProveedores() {
		int count = proveedorService.proveedorCount();
		assertNotEquals(0, count);
	}
	
	@Test
	public void testFindProveedorById() {
		Proveedor provFind = proveedorService.findProveedorById(1).get();
		assertEquals(Proveedor.class, provFind.getClass());
	}
	
	@Test
	public void testSaveProveedor() {
		Proveedor proveedorNew = new Proveedor();
		proveedorNew.setName("Roberto");
		proveedorNew.setEmail("robertito@gamil.com");
		proveedorNew.setDireccion("Calle Holanda 16");
		proveedorNew.setTelefono("645101010");

		proveedorService.saveProveedor(proveedorNew);
		
		Integer cantidad = proveedorService.proveedorCount();
		assertEquals(3, cantidad);
	}
	
	@Test
	public void testDeleteProveedorById() {
		proveedorService.deleteById(1);
		assertEquals(false, proveedorService.findProveedorById(1).isPresent());
	}
	
	//Test negativos
	
	@Test
	public void testNotFindProveedorById() {
		Optional<Proveedor> provFind = proveedorService.findProveedorById(50);
		assertEquals(false, provFind.isPresent());
	}
	
}
