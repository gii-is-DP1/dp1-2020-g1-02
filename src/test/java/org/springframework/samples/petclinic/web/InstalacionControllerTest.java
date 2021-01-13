package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Instalacion;
import org.springframework.samples.petclinic.service.InstalacionService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= InstalacionController.class,
includeFilters= @ComponentScan.Filter(value = Instalacion.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class InstalacionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private InstalacionService instalacionService;
	
	private Instalacion instalacion;
	
	@BeforeEach
	void setup() {
		instalacion = new Instalacion();
		instalacion.setId(1);
		instalacion.setDimension(200.4);
		instalacion.setLugar("Calle Ave del Paraiso 23");
		
		given(this.instalacionService.findInstalacionById(1)).willReturn(Optional.of(instalacion));
		
		List<Instalacion> instalaciones = new ArrayList<Instalacion>();
		instalaciones.add(instalacion);
		given(this.instalacionService.findAll()).willReturn(instalaciones);
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoInstalaciones() throws Exception{
		mockMvc.perform(get("/instalaciones")).andExpect(status().isOk())
		.andExpect(model().attributeExists("instalacion"))
		.andExpect(view().name("instalaciones/listadoInstalaciones"));
	}

}
