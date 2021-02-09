package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.User;
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
		User user = new User();
		Authorities a = new Authorities();
		a.setAuthority("administrador");
		user.setUsername("admin111");
		user.setPassword("aaaaaaaaaaA1");
		user.setAuthorities(a);
		user.setEnabled(true);
		
		Administrador administradorNew = new Administrador();
		administradorNew.setNombre("Pablo");
		administradorNew.setApellidos("Sánchez");
		administradorNew.setCorreo("pablo2@gmail.com");
		administradorNew.setDireccion("Calle Sevilla");
		administradorNew.setDni("47390692C");
		administradorNew.setTelefono("678345901");
		administradorNew.setTipocategoria(TipoCategoria.Mantenimiento);
		administradorNew.setUser(user);
		

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
	
	@Test
	public void testSaveAdministradorConUserNameRepetido() {
		User user = new User();
		Authorities a = new Authorities();
		a.setAuthority("administrador");
		user.setUsername("admin");
		user.setPassword("aaaaaaaaaaA1");
		user.setAuthorities(a);
		user.setEnabled(true);
		
		Administrador administradorNew = new Administrador();
		administradorNew.setNombre("Pablo");
		administradorNew.setApellidos("Sánchez");
		administradorNew.setCorreo("pablo2@gmail.com");
		administradorNew.setDireccion("Calle Sevilla");
		administradorNew.setDni("47390692C");
		administradorNew.setTelefono("678345901");
		administradorNew.setTipocategoria(TipoCategoria.Mantenimiento);
		administradorNew.setUser(user);
		assertThrows(DataIntegrityViolationException.class, ()->administradorService.save(administradorNew));
	}

}
