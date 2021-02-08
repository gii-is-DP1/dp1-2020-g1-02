package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ProveedorServiceTest2 {

	@Mock
	private ProveedorRepository provRepo;
	
	@Autowired
	protected ProveedorService provService;
	
	@BeforeEach
	void setup() {
		
		provService = new ProveedorService(provRepo);	
		
	}
	
	@Test
	void testAñadirProveedor() {
		Proveedor newProv = new Proveedor();
		newProv.setName("Pablo");
		newProv.setEmail("pablog@gmail.com");
		newProv.setTelefono("633444555");
		newProv.setDireccion("Calle Conventual 17");
		
		Collection<Proveedor> proveedores = new ArrayList<Proveedor>();
		proveedores.add(newProv);
		when(provRepo.findAll()).thenReturn(proveedores);
		
		List<Proveedor> provs = StreamSupport.stream(this.provRepo.findAll().spliterator(), false).collect(Collectors.toList());
		Proveedor provAñadido = provs.get(provs.size()-1);
		assertTrue(provAñadido.getName().equals("Pablo"));
		assertTrue(provAñadido.getEmail().equals("pablog@gmail.com"));
		assertTrue(provAñadido.getTelefono().equals("633444555"));
		assertTrue(provAñadido.getDireccion().equals("Calle Conventual 17"));
	
	}
	
	@Test 
	void testFindProveedorById() {
		Proveedor prov1 = new Proveedor();
		prov1.setId(1);
		prov1.setName("Lejias SL");
		prov1.setTelefono("645681128");
		prov1.setEmail("lejiassl@gmail.com");
		prov1.setDireccion("Calle Reina 14, Sevilla");
		
		Proveedor prov2 = new Proveedor();
		prov2.setId(2);
		prov2.setName("Jabon SA");
		prov2.setTelefono("633681128");
		prov2.setEmail("jabonsa@gmail.com");
		prov2.setDireccion("Calle Mercedes 6, Sevilla");
		
		given(this.provRepo.findById(2)).willReturn(Optional.of(prov2));
		
		Optional<Proveedor> provPrueba = this.provService.findProveedorById(2);
		
		assertThat(provPrueba.get().getName().equals("Jabones SA"));
		assertTrue(provPrueba.get().getEmail().equals("jabonsa@gmail.com"));
		assertTrue(provPrueba.get().getTelefono().equals("633681128"));
		assertTrue(provPrueba.get().getDireccion().equals("Calle Mercedes 6, Sevilla"));
	}
}
