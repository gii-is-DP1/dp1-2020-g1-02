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
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void testCountWithInitialData() {
		int count = userService.eventCount();
		assertEquals(count, 8);
	}
	
	@Test
	public void testSaveUser() {
		//Arrange
		User user = new User();
		Authorities authorities = new Authorities();
		authorities.setAuthority("administrador");
		user.setUsername("Batman");
		user.setPassword("NoSoyBruceWayne");
		user.setAuthorities(authorities);
		//Act
		userService.saveUser(user);
		int count = userService.eventCount();
		//Assert
		assertEquals(count, 9);
		
	}
	
	@Test
	public void testGetUserById() {
		//Arrange
		String id = "Ironman";
		//Act
		Optional<User> user = userService.findUser(id);
		//Assert
		assertNotNull(user.get());
	}
	
	@Test
	public void testGetUserByIdNotFound() {
		//Arrange
		String id = "drgdfvrwfsdf";
		//Act
		Optional<User> user = userService.findUser(id);
		//Assert
		assertThrows(NoSuchElementException.class, () -> {user.get();});
	}
	
	@Test
	public void testUpdateUser() {
		//Arrange
		String id = "Ironman";
		//Act
		Optional<User> user = userService.findUser(id);
		Authorities authorities = new Authorities();
		authorities.setAuthority("administrador");
		user.get().setAuthorities(authorities);
		Optional<User> userActualizado = userService.findUser(id);
		//Assert
		assertEquals(userActualizado.get().getAuthorities(), authorities);
	}

}
