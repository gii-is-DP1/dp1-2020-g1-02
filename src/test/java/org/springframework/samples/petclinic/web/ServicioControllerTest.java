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
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Presupuesto;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.TipoPresupuesto;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.InstalacionService;
import org.springframework.samples.petclinic.service.PresupuestoService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= ServicioController.class,
includeFilters= @ComponentScan.Filter(value = Servicio.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class ServicioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ServicioService servicioService;
	@MockBean
	private PresupuestoService presupuestoService;
	@MockBean 
	private ClienteService clienteService;
	
	@MockBean
	private UserService userService;
	
	private Servicio servicio;
	private Presupuesto presupuesto;
	
	@BeforeEach
	void setup() {
		servicio = new Servicio();
		servicio.setId(1);
		servicio.setLugar("Acuario de Sevilla");
		servicio.setTipocategoria(TipoCategoria.Cristaleria);
		servicio.setEstado(EstadoServicio.Aceptado);
		servicio.setFechainicio(LocalDate.of(2020, 12, 31));
		servicio.setFechafin(LocalDate.of(2021, 01, 12));
		
		given(this.servicioService.findServicioById(1)).willReturn(Optional.of(servicio));
		List<Servicio> servicios = new ArrayList<Servicio>();
		servicios.add(servicio);
		given(this.servicioService.findAll()).willReturn(servicios);
		
		presupuesto = new Presupuesto();
		presupuesto.setId(1);
		presupuesto.setEstado(EstadoServicio.Espera);
		presupuesto.setPrecio(800.0);
		presupuesto.setTipopresupuesto(TipoPresupuesto.Cerrado);
		presupuesto.setServicio(servicio);
		
		given(this.presupuestoService.findPresupuestoById(1)).willReturn(Optional.of(presupuesto));
		List<Presupuesto> presupuestos= new ArrayList<Presupuesto>();
		presupuestos.add(presupuesto);
		given(this.presupuestoService.findAll()).willReturn(presupuestos);
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoServicios() throws Exception{
		mockMvc.perform(get("/servicios")).andExpect(status().isOk())
		.andExpect(model().attributeExists("servicios"))
		.andExpect(view().name("servicios/listadoServicios"));
	}
	
	@WithMockUser(value="spring")
	@Test
	void testNewServicio() throws Exception{
		mockMvc.perform(get("/servicios/new")).andExpect(status().isOk()).andExpect(model().attributeExists("servicio")).andExpect(view().name("servicios/editServicio"));
	}
	
	@WithMockUser(value=("spring"))
	@Test
	void testSaveServicio() throws Exception {
		mockMvc.perform(post("/servicios/save").with(csrf())
			.param("id", "1")
			.param("estado", "Espera")
			.param("lugar", "Promociones Sanlucar")
			.param("tipocategoria", "Limpieza")
			.param("fechainicio", "2020/01/01")
			.param("fechafin", "2020/02/01"))
		.andExpect(status().is2xxSuccessful()).andExpect(view().name("redirect:/servicios/misServicios"));
	}
	

	
	@WithMockUser(value="spring")
	@Test
	void testAceptarServicio() throws Exception {
		mockMvc.perform(post("/servicios/aceptar").with(csrf())
			.param("id","1"))
		.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/servicios/1/presupuestos/new"));
	}
	
	@WithMockUser(value="spring")
	@Test
	void testRechazarServicio() throws Exception {
		mockMvc.perform(post("/servicios/rechazar").with(csrf())
			.param("id","1"))
		.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/servicios"));
	}
	
	@WithMockUser(value="spring")
	@Test
	void testCrearPresupuesto() throws Exception {
		mockMvc.perform(get("/servicios/{servicioId}/presupuestos/new", 1)).andExpect(status().isOk()).andExpect(model().attributeExists("presupuesto")).andExpect(view().name("presupuestos/editPresupuesto"));
	}
	
//	@WithMockUser(value="spring")
//	@Test
//	void testSavePresupuesto() throws Exception {
//		mockMvc.perform(post("/servicios/presupuestos/save").with(csrf())
//				.param("estado", "Espera")
//				.param("precio", "12.0")
//				.param("tipopresupuesto", "PorHoras")
//				.param("servicio", "1"))
//		.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/servicios/1/presupuestos"));
//	}
	
	@WithMockUser(value="spring")
	@Test
	void testAceptarPresupuesto() throws Exception {
		mockMvc.perform(post("/servicios/{presupuestoId}/aceptar", 1).with(csrf())
			.param("id","1"))
		.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/servicios/1/presupuestos"));
	}
	
	@WithMockUser(value="spring")
	@Test
	void testRechazarPresupuesto() throws Exception {
		mockMvc.perform(post("/servicios/{presupuestoId}/rechazar", 1).with(csrf())
			.param("id","1"))
		.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/servicios/1/presupuestos"));
	}
	
	
}
