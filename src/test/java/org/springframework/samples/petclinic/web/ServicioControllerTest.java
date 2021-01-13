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
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.w3c.dom.ls.LSInput;

@WebMvcTest(controllers= ServicioController.class,
includeFilters= @ComponentScan.Filter(value = Servicio.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class ServicioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ServicioService servicioService;
	
	private Servicio servicio;
	
	@BeforeEach
	void setup() {
		servicio = new Servicio();
		servicio.setId(1);
		servicio.setLugar("Acuario de Sevilla");
		servicio.setTipocategoria(TipoCategoria.Lavanderia);
		servicio.setEstado(EstadoServicio.Aceptado);
		servicio.setFechainicio(LocalDate.of(2020, 12, 31));
		servicio.setFechafin(LocalDate.of(2021, 01, 12));
		
		given(this.servicioService.findServicioById(1)).willReturn(Optional.of(servicio));
		
		List<Servicio> servicios = new ArrayList<Servicio>();
		servicios.add(servicio);
		given(this.servicioService.findAll()).willReturn(servicios);
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoServicios() throws Exception{
		mockMvc.perform(get("/servicios")).andExpect(status().isOk())
		.andExpect(model().attributeExists("servicios"))
		.andExpect(view().name("servicios/listadoServicios"));
	}

}
