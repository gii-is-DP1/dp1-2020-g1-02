package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ServicioServiceTest {
	
	@Autowired
	private ServicioService servicioService;
	
	//Test positivos
	
	@Test
	public void testCountWithInitialData() {
		int count = servicioService.servicioCount();
		assertEquals(count, 5);
	}
	
	@Test
	public void testSaveService() {
		//Arrange
		Servicio ser1 = new Servicio();
		ser1.setLugar("Lope de Vega");
		ser1.setFechafin(LocalDate.of(2020, 12, 12));
		ser1.setFechainicio(LocalDate.of(2019, 12, 12));
		ser1.setEstado(EstadoServicio.Espera);
		ser1.setTipocategoria(TipoCategoria.Lavanderia);
		servicioService.save(ser1);
		int count = servicioService.servicioCount();
		//Assert
		assertEquals(count, 6);
		
	}
	
	@Test
	public void testGetServiceById() {
		//Arrange
		int id = 1;
		//Act
		Optional<Servicio> ser1 = servicioService.findServicioById(id);
		//Assert
		assertNotNull(ser1);
	}
	
	@Test
	public void testGetServicesByCliente() {
		//Arrange
		int id = 1;
		//Act
		Iterable<Servicio> ser1 = servicioService.serviciosByCliente(id);
		//Assert
		assertNotNull(ser1);
		//assertNotNull(ser1);
	}
	
	
	
	@Test
	public void testDeleteServicio() {
		//Arrange
		Optional<Servicio> servicio = servicioService.findServicioById(1);
		//Act
		servicioService.delete(servicio.get());
		int count = servicioService.servicioCount();
		//Assert
		assertEquals(count, 3);
		
	}
	
	@Test
	public void testUpdateTrabajador() {
		//Arrange
		int id = 1;
		//Act
		Optional<Servicio> servicio = servicioService.findServicioById(id);
		servicio.get().setLugar("Sevilla");
		Optional<Servicio> trabajadorActualizado = servicioService.findServicioById(id);
		//Assert
		assertEquals(trabajadorActualizado.get().getLugar(), "Sevilla");
		
	}
	
	//Test negativos
	
	@Test
	public void testGetServiceByIdNotFound() {
		//Arrange
		int id = 65;
		//Act
		Optional<Servicio> ser1 = servicioService.findServicioById(id);
		//Assert
		assertThrows(NoSuchElementException.class, () -> {ser1.get();});
	}
}
