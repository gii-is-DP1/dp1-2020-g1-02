package org.springframework.samples.petclinic.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ProveedorTest {

	@Autowired
	private ProveedorService proveedorService;
	
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
	
//	@Test
//	public void testSaveProveedor() {
//		Proveedor proveedorNew = new Proveedor();
//		proveedorNew.setId(10);
//		proveedorNew.setName("Roberto");
//		proveedorNew.setEmail("robertito@gamil.com");
//		proveedorNew.setDireccion("Calle Holanda 16");
//		proveedorNew.setTelefono("645101010");
//
//		proveedorService.save(proveedorNew);
//		
//		assertEquals(Proveedor.class, proveedorService.findProveedorById(10).get().getClass());
//	}
	
	@Test
	public void testDeleteProveedorById() {
		proveedorService.deleteById(1);
		assertEquals(true, proveedorService.findProveedorById(1).isEmpty());
	}
}
