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
import org.springframework.samples.petclinic.model.RegistroHoras;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class RegistroHorasServiceTest {

	@Autowired
	private RegistroHorasService registroHorasService;
	
	//Test positivos
	
	@Test
	public void testExistenRegistroHoras() {
		int count = registroHorasService.RegistroHorasCount();
		assertEquals(count, 2);
	}
	
	@Test
	public void testDeleteRegistroHorasById() {
		registroHorasService.deleteById(1);
		assertEquals(false, registroHorasService.findRegistroHorasById(1).isPresent());
	}
	
	@Test
	public void testSaveRegistroHoras() throws ParseException {
		RegistroHoras registroHorasNew = new RegistroHoras();
		
		registroHorasNew.setFecha(LocalDate.of(2021, 07,25));
		registroHorasNew.setHora_inicio(LocalTime.of(12, 00));
		registroHorasNew.setHora_fin(LocalTime.of(15, 00));
		

		registroHorasService.saveRegistroHoras(registroHorasNew);
		
		Integer cantidad = registroHorasService.RegistroHorasCount();
		assertEquals(3, cantidad);
	}
	
	
	@Test
	public void testRegistroHorasFindById() {
		RegistroHoras registroHorasFind = registroHorasService.findRegistroHorasById(1).get();
		assertEquals(RegistroHoras.class, registroHorasFind.getClass());
	}
	
	//Test negativos
	
	@Test
	public void testNotFindRegistroHorasById() {
		Optional<RegistroHoras> registroHorasFind = registroHorasService.findRegistroHorasById(50);
		assertEquals(false, registroHorasFind.isPresent());
	}
}
