package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Curriculum;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CurriculumServiceTest {

	@Autowired
	private CurriculumService curriculumService;
	
	@Test
	public void testExistenCurriculums() {
		int count = curriculumService.curriculumCount();
		assertEquals(count, 2);
	}
	
	@Test
	public void testDeleteCurriculumById() {
		curriculumService.deleteById(1);
		assertEquals(false, curriculumService.findCurriculumById(1).isPresent());
	}
	
	@Test
	public void testSaveCurriculum() {
		Curriculum curriculumNew = new Curriculum();
		curriculumNew.setNombre("Jesús");
		curriculumNew.setTipocategoria(TipoCategoria.Limpieza);
		curriculumNew.setId_trab(3);

		curriculumService.save(curriculumNew);
		
		Integer cantidad = curriculumService.curriculumCount();
		assertEquals(3, cantidad);
	}
	
	@Test
	public void testFindAll() {
		curriculumService.findAll();
	}
	
	@Test
	public void testFindAllCurriculumsByTrabajadorId() {
		Boolean i = true;
		Iterable<Curriculum> curriculumFind = curriculumService.findCurriculumByTrabajadorId("");
		Iterator<Curriculum> iterador = curriculumFind.iterator();
		while(iterador.hasNext()) {
			if(iterador.next().getId_trab() != 1) {
				i =false;
			}
		}	
		assertTrue(i);
	}
	
	@Test
	public void testCurriculumFindById() {
		Curriculum curriculumFind = curriculumService.findCurriculumById(1).get();
		assertEquals(Curriculum.class, curriculumFind.getClass());
	}
	
	
}
