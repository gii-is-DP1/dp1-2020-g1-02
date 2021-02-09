package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

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
	
	//Test positivos
	
	@Test
	public void testExistenCurriculums() {
		int count = curriculumService.curriculumCount();
		assertEquals(count, 5);
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
		curriculumNew.setApellidos("Villa");
		curriculumNew.setTelefono("666666666");
		curriculumNew.setCorreo("carvilgar1@gmail.com");
		curriculumNew.setDescripcion("Holaaaaaaaaaaaa");
		curriculumNew.setTipocategoria(TipoCategoria.Limpieza);

		curriculumService.save(curriculumNew);
		
		Integer cantidad = curriculumService.curriculumCount();
		assertEquals(6, cantidad);
	}
	
	@Test
	public void testCurriculumFindById() {
		Curriculum curriculumFind = curriculumService.findCurriculumById(1).get();
		assertEquals(Curriculum.class, curriculumFind.getClass());
	}
	
	//Test negativos
	
	@Test
	public void testNotFindCurriculumById() {
		Optional<Curriculum> curriculumFind = curriculumService.findCurriculumById(50);
		assertEquals(false, curriculumFind.isPresent());
	}
	
	@Test
	public void testSaveCurriculumConError() {
		Curriculum curriculumNew = new Curriculum();
		curriculumNew.setNombre("Jesus");
		curriculumNew.setApellidos("Villa");
		curriculumNew.setTelefono("666666666");
		curriculumNew.setCorreo("");
		curriculumNew.setDescripcion("Holaaaaaaaaaaaa");
		curriculumNew.setTipocategoria(TipoCategoria.Limpieza);
		assertThrows(ConstraintViolationException.class, ()->curriculumService.save(curriculumNew));
	}
}
