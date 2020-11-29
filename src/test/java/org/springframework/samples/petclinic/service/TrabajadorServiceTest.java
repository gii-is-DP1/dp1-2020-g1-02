package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TrabajadorServiceTest {
	
	@Autowired
	private TrabajadorService trabajadorService;
	
	@Test
	public void testCountWithInitialData() {
		int count = trabajadorService.eventCount();
		assertEquals(count, 2);
	}
	
	@Test
	public void testSaveTrabajador() {
		//Arrange
		Trabajador tr1 = new Trabajador();
		tr1.setNombre("Trabajador1");
		tr1.setApellidos("Trabajador1");
		tr1.setDni("2556895C");
		tr1.setCorreo("hjhjhjh@lk.com");
		tr1.setTelefono("666666666");
		tr1.setTipocategoria("Limpieza");
		//Act
		trabajadorService.save(tr1);
		int count = trabajadorService.eventCount();
		//Assert
		assertEquals(count, 3);
		
	}
	
	@Test
	public void testGetTrabajadorById() {
		//Arrange
		int id = 2;
		//Act
		Optional<Trabajador> tr1 = trabajadorService.findTrabajadorById(id);
		//Assert
		assertNotNull(tr1.get());
	}
	
	@Test
	public void testGetTrabajadorByIdNotFound() {
		//Arrange
		int id = 65;
		//Act
		Optional<Trabajador> tr1 = trabajadorService.findTrabajadorById(id);
		//Assert
		assertThrows(NoSuchElementException.class, () -> {tr1.get();});
	}
	
	@Test
	public void testDeleteTrabajador() {
		//Arrange
		Optional<Trabajador> trabajador = trabajadorService.findTrabajadorById(2);
		//Act
		trabajadorService.delete(trabajador.get());
		int count = trabajadorService.eventCount();
		//Assert
		assertEquals(count, 1);
		
	}
	
	@Test
	public void testUpdateTrabajador() {
		//Arrange
		int id = 2;
		//Act
		Optional<Trabajador> trabajador = trabajadorService.findTrabajadorById(id);
		trabajador.get().setNombre("Manolo");
		Optional<Trabajador> trabajadorActualizado = trabajadorService.findTrabajadorById(id);
		//Assert
		assertEquals(trabajadorActualizado.get().getNombre(), "Manolo");
		
	}
}
