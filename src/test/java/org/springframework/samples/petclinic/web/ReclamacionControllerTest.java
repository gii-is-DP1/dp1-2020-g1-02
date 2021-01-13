package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
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
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= ReclamacionController.class,
includeFilters= @ComponentScan.Filter(value = Reclamacion.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class ReclamacionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ReclamacionService reclamacionService;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private ServicioService servicioService;
	
	private Reclamacion reclamacion;
	
	@BeforeEach
	void setup() {
		reclamacion = new Reclamacion();
		reclamacion.setId(1);
		reclamacion.setFecha(LocalDate.of(2020, 11, 11));
		reclamacion.setDescripcion("Trabajo incompleto");
		
		given(this.reclamacionService.findReclamacionById(1)).willReturn(Optional.of(reclamacion));
		
		List<Reclamacion> reclamaciones = new ArrayList<Reclamacion>();
		reclamaciones.add(reclamacion);
		given(this.reclamacionService.findAll()).willReturn(reclamaciones);
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoReclamaciones() throws Exception{
		mockMvc.perform(get("/reclamaciones")).andExpect(status().isOk())
		.andExpect(model().attributeExists("reclamaciones"))
		.andExpect(view().name("reclamaciones/listadoReclamaciones"));
	}
	

}
