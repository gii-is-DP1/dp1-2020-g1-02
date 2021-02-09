package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class OfertaServiceTest {
	
	@Autowired
	private OfertaService ofertaService;
	
	//Test positivos
	
	@Test
	public void testExistenOfertas() {
		int count = ofertaService.ofertasCount();
		assertNotEquals(0, count);
	}
	
	@Test
	public void testEncuentraOfertaById() {
		Oferta ofertaFind = ofertaService.findOfertaById(1).get();
		assertEquals(1, ofertaFind.getId());
	}
	
	@Test
	public void testGuardarOferta() {
		Proveedor p=new Proveedor();
		p.setId(1);
		p.setName("Pablo");
		p.setTelefono("633444555");
		p.setEmail("pablog@gmail.com");
		p.setDireccion("Calle Conventual 17");
		p.setFacturas(Lists.list(new Factura()));
		
		Oferta ofertaGuardar = new Oferta();
		ofertaGuardar.setName("lejia");
		ofertaGuardar.setPrecioU(2.85);
		ofertaGuardar.setProveedor(p);
		ofertaService.save(ofertaGuardar);
		int count = ofertaService.ofertasCount();
		assertEquals(count, 5);
	}
	
	@Test
	public void testBorrarOfertaById() {
		Oferta ofertaBorrar = ofertaService.findOfertaById(1).get();
		ofertaService.delete(ofertaBorrar);
		assertEquals(false, ofertaService.findOfertaById(1).isPresent());
	}
	
	
	
	//Test negativos
	@Test
	public void testNoEncuentraOfertaById() {
		Optional<Oferta> ofertaFind = ofertaService.findOfertaById(50);
		assertEquals(false, ofertaFind.isPresent());
	}

}
