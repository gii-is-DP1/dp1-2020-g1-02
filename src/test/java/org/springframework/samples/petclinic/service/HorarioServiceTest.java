package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HorarioServiceTest {

	@Autowired
	private HorarioService horarioService;
	
	//Test positivos
	
	@Test
	public void testExistenHorario() {
		int count = horarioService.horarioCount();
		assertEquals(count, 2);
	}
	
	@Test
	public void testDeleteHorarioById() {
		horarioService.deleteById(1);
		assertEquals(false, horarioService.findHorarioById(1).isPresent());
	}
	
	@Test
	public void testSaveHorario() throws ParseException {
		Horario horarioNew = new Horario();
	
		
		horarioNew.setFecha(LocalDate.of(2021, 07,25));
		horarioNew.setHora_inicio(LocalTime.of(12, 00));
		horarioNew.setHora_fin(LocalTime.of(15, 00));
		horarioNew.setDescripcion("En acuario de Sevilla");

		horarioService.save(horarioNew);
		
		Integer cantidad = horarioService.horarioCount();
		assertEquals(3, cantidad);
	}
	
	
	@Test
	public void testHorarioFindById() {
		Horario horarioFind = horarioService.findHorarioById(1).get();
		assertEquals(Horario.class, horarioFind.getClass());
	}
	
	//Test negativos
	
	@Test
	public void testNotFindHorarioById() {
		Optional<Horario> horarioFind = horarioService.findHorarioById(50);
		assertEquals(false, horarioFind.isPresent());
	}
}
