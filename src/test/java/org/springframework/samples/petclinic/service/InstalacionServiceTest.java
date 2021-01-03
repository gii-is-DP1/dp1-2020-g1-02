package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Instalacion;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class InstalacionServiceTest {
	
	@Autowired
	private InstalacionService instalacionService;
	
	//Test positivos
	
	@Test
	public void testExistenInstalaciones() {
		int count = instalacionService.instalacionCount();
		assertEquals(count, 3);
	}
	
	@Test
	public void testDeleteInstalacionById() {
		instalacionService.deleteById(1);
		assertEquals(false, instalacionService.findInstalacionById(1).isPresent());
	}
	
	@Test
	public void testSaveInstalacion() {
		Instalacion instalacionNew = new Instalacion();
		instalacionNew.setLugar("La Palmera Sevilla");
		instalacionNew.setDimension(3.87);
		
		instalacionService.save(instalacionNew);
		
		Integer cantidad = instalacionService.instalacionCount();
		assertEquals(4, cantidad);
	}
	
	
	@Test
	public void testInstalacionFindById() {
		Instalacion instalacionFind = instalacionService.findInstalacionById(1).get();
		assertEquals(Instalacion.class, instalacionFind.getClass());
	}
	
	//Test negativos
	
	@Test
	public void testNotFindInstalacionById() {
		Optional<Instalacion> instalacionFind = instalacionService.findInstalacionById(50);
		assertEquals(false, instalacionFind.isPresent());
	}

}
