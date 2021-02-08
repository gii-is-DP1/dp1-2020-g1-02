package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Valoracion;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ValoracionServiceTest {
	
	@Autowired
	private ValoracionService valoracionService;
	
	//Test positivos
	
	@Test
	public void testExistenValoraciones() {
		int count = valoracionService.valoracionCount();
		assertEquals(count, 5);
	}
	
	@Test
	public void testDeleteValoracionById() {
		valoracionService.deleteById(1);
		assertEquals(false, valoracionService.findValoracionById(1).isPresent());
	}
	
	@Test
	public void testSaveValoracion() {
		Valoracion valoracionNew = new Valoracion();
		valoracionNew.setFecha(LocalDate.of(2019, 03, 31));
		valoracionNew.setValor(5);

		valoracionService.save(valoracionNew);
		
		Integer cantidad = valoracionService.valoracionCount();
		assertEquals(6, cantidad);
	}
	
	@Test
	public void testValoracionFindById() {
		Valoracion valoracionFind = valoracionService.findValoracionById(1).get();
		assertEquals(Valoracion.class, valoracionFind.getClass());
	}
	
//	@Test 
//	public void testGetMediaValoracion() {
//		Integer media = valoracionService.getMediaValoraciones(TipoCategoria.Limpieza);
//		
//	}
	
	//Test negativos
	
	@Test
	public void testNotFindValoracionById() {
		Optional<Valoracion> valoracionFind = valoracionService.findValoracionById(50);
		assertEquals(false, valoracionFind.isPresent());
	}

}
