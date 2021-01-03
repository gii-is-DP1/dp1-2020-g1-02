package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ReclamacionServiceTest {

	@Autowired
	private ReclamacionService reclamacionService;
	
	//Test positivos
	
	@Test
	public void testExistenReclamaciones() {
		int count = reclamacionService.reclamacionCount();
		assertEquals(count, 2);
	}
	
	@Test
	public void testDeleteReclamacionById() {
		reclamacionService.deleteById(1);
		assertEquals(false, reclamacionService.findReclamacionById(1).isPresent());
	}
	
	@Test
	public void testSaveReclamacion() {
		Reclamacion reclamacionNew = new Reclamacion();
		reclamacionNew.setFecha(LocalDate.now());
		reclamacionNew.setDescripcion("No llegó a la hora acordada");

		reclamacionService.save(reclamacionNew);
		
		Integer cantidad = reclamacionService.reclamacionCount();
		assertEquals(3, cantidad);
	}
	
	
	@Test
	public void testReclamacionFindById() {
		Reclamacion reclamacionFind = reclamacionService.findReclamacionById(1).get();
		assertEquals(Reclamacion.class, reclamacionFind.getClass());
	}
	
	//Test negativos
	
	@Test
	public void testNotFindReclamacionById() {
		Optional<Reclamacion> reclamacionFind = reclamacionService.findReclamacionById(50);
		assertEquals(false, reclamacionFind.isPresent());
	}
}
