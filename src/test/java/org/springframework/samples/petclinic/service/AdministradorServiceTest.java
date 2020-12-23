package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AdministradorServiceTest {
	
	@Autowired
	private AdministradorService administradorService;
	
	//Test positivos
	
	@Test
	public void testExistenAdministrador() {
		int count = administradorService.administradorCount();
		assertEquals(count, 2);
	}
	
	@Test
	public void testDeleteAdministradorById() {
		administradorService.deleteById(1);
		assertEquals(false, administradorService.findAdministradorById(1).isPresent());
	}
	
	@Test
	public void testSaveAdministrador() {
		Administrador administradorNew = new Administrador();
		administradorNew.setNombre("Pablo");
		administradorNew.setApellidos("Sánchez");
		administradorNew.setTipocategoria(TipoCategoria.Mantenimiento);

		administradorService.save(administradorNew);
		
		Integer cantidad = administradorService.administradorCount();
		assertEquals(3, cantidad);
	}
	
	
	@Test
	public void testAdministradorFindById() {
		Administrador administradorFind = administradorService.findAdministradorById(1).get();
		assertEquals(Administrador.class, administradorFind.getClass());
	}
	
	//Test negativos
	
	@Test
	public void testNotFindAdministradorById() {
		Optional<Administrador> adminFind = administradorService.findAdministradorById(50);
		assertEquals(false, adminFind.isPresent());
	}
	

}
