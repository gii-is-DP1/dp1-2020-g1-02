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
import org.springframework.samples.petclinic.model.Presupuesto;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.TipoPresupuesto;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PresupuestoServiceTest {
	
	@Autowired
	private PresupuestoService presupuestoService;
	
	//Test positivos
	
	@Test
	public void testCountWithInitialData() {
		int count = presupuestoService.presupuestoCount();
		assertEquals(count, 5);
	}
	
	@Test
	public void testSavePresupuesto() {
		//Arrange
		Servicio ser1 = new Servicio();
		ser1.setId(1);
		ser1.setLugar("Lope de Vega");
		ser1.setFechafin(LocalDate.of(2020, 12, 12));
		ser1.setFechainicio(LocalDate.of(2019, 12, 12));
		ser1.setEstado(EstadoServicio.Espera);
		ser1.setTipocategoria(TipoCategoria.Jardineria);
		
		Presupuesto p=new Presupuesto();
		p.setId(1);
		p.setEstado(EstadoServicio.Aceptado);
		p.setPrecio(10.0);
		p.setTipopresupuesto(TipoPresupuesto.PorHoras);
		p.setServicio(ser1);
		presupuestoService.save(p);
		int count = presupuestoService.presupuestoCount();
		//Assert
		assertEquals(count, 5);
		
	}
		
	@Test
	public void testGetPresupuestoById() {
		//Arrange
		Integer id = 1;
		//Act
		Presupuesto p = presupuestoService.findPresupuestoById(id).get();
		//Assert
		assertNotNull(p);
	}
	
	@Test
	public void testGetPresupuestoByServicio() {
		//Arrange
		int id = 1;
		//Act
		Iterable<Presupuesto> p = presupuestoService.presupuestosByServicio(id);
		//Assert
		assertNotNull(p);
	}
	
	@Test
	public void testDeleteServicio() {
		//Arrange
		Presupuesto p = presupuestoService.findPresupuestoById(1).get();
		//Act
		presupuestoService.delete(p);
		int count = presupuestoService.presupuestoCount();
		//Assert
		assertEquals(count, 4);
		
	}
	
	@Test
	public void testNumeroPresupuestosByServicioConEstadoAceptado() {
		//Arrange
		int id = 1;
		//Act
		Integer p =presupuestoService.numeroPresupuestosByServicioConEstadoAceptado(id);
		
		//Assert
		assertEquals(p, 1);
	}
	
	
	//Test negativos
	
	@Test
	public void testGetPresupuestoByIdNotFound() {
		//Arrange
		int id = 65;
		//Act
		Optional<Presupuesto> ser1 = presupuestoService.findPresupuestoById(id);
		//Assert
		assertThrows(NoSuchElementException.class, () -> {ser1.get();});
	}
}
