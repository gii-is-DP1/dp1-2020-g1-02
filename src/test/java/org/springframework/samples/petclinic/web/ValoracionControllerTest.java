package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.samples.petclinic.model.NivelSatisfaccion;
import org.springframework.samples.petclinic.model.Valoracion;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.samples.petclinic.service.ValoracionService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= ValoracionController.class,
includeFilters= @ComponentScan.Filter(value = ValoracionValidator.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class ValoracionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ValoracionService valoracionService;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private ServicioService servicioService;
	
	private Valoracion valoracion;
	
	@BeforeEach
	void setup() {
		//Preparacion de la valoración
		valoracion = new Valoracion();
		valoracion.setId(1);
		valoracion.setFecha(LocalDate.of(2019, 05, 15));
		valoracion.setNivelsatisfaccion(NivelSatisfaccion.Medio);
		
		given(this.valoracionService.findValoracionById(1)).willReturn(Optional.of(valoracion));
		
		List<Valoracion> valoraciones = new ArrayList<Valoracion>();
		valoraciones.add(valoracion);
		given(this.valoracionService.findAll()).willReturn(valoraciones);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEditValoracion() throws Exception{
		mockMvc.perform(get("/valoraciones/new")).andExpect(status().isOk()).andExpect(model().attributeExists("valoracion"))
		.andExpect(view().name("valoraciones/newValoracion"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoValoraciones() throws Exception{
		mockMvc.perform(get("/valoraciones")).andExpect(status().isOk())
		.andExpect(model().attributeExists("valoraciones"))
		.andExpect(view().name("valoraciones/listadoValoraciones"));
	}
	
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/valoraciones/save")
						.with(csrf())
						.param("fecha", "LocalDate.of(2020, 04, 09)")
						.param("nivel_satisfaccion", "NivelSatisfaccion.Bajo"))	
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("valoraciones/newValoracion"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/valoraciones/save")
						.with(csrf())
						.param("fecha", "")
						.param("nivel_satisfaccion", "NivelSatisfaccion.Bajo"))	
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("valoracion"))
			.andExpect(model().attributeHasFieldErrors("valoracion", "fecha"))
			.andExpect(view().name("valoraciones/newValoracion"));
	}

}
