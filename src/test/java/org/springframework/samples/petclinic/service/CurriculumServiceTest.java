package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Curriculum;
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
		assertEquals(true, curriculumService.findCurriculumById(1).isEmpty());
	}
	
	@Test
	public void testSaveCurriculumById() {
		curriculumService.saveById(1);
		assertEquals(false, curriculumService.findCurriculumById(1).isEmpty());
	}
	
	@Test
	public void testFindAll() {
		curriculumService.findAll();
	}
	
	@Test
	public void testFindAllCurriculumsByTrabajadorId() {
		Boolean i = true;
		Iterable<Curriculum> curriculumFind = curriculumService.findCurriculumsByTrabajadorId(1);
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
