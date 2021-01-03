package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.RegistroHoras;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ResgitroHorasServiceTest {

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
	public void testSaveRegistroHoras() {
		RegistroHoras registroHorasNew = new RegistroHoras();
		registroHorasNew.setHora_entrada(LocalDateTime.MAX);
		registroHorasNew.setHora_salida(LocalDateTime.MIN);

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
