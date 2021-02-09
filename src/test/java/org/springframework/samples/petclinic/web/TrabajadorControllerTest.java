package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= TrabajadorController.class,
			includeFilters= @ComponentScan.Filter(value = TrabajadorValidator.class, type = FilterType.ASSIGNABLE_TYPE ),
			excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
			excludeAutoConfiguration= SecurityConfiguration.class)

public class TrabajadorControllerTest {
	

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TrabajadorService trabajadorService;
	@MockBean
	private HorarioService horarioService;
	@MockBean
	private ServicioService servicioService;
	
	private Trabajador trabajador;
	private Horario horario;
	private Servicio servicio;
	
	@BeforeEach
	void setup() {
		//Preparacion del trabajador
		trabajador = new Trabajador();
		trabajador.setId(1);
		trabajador.setDni("36578363P");
		trabajador.setNombre("Francisco");
		trabajador.setApellidos("García");
		trabajador.setTelefono("668368479");
		trabajador.setDireccion("Calle Santana 12");
		trabajador.setCorreo("frangar@gmail.com");
		trabajador.setTipocategoria(TipoCategoria.Cristaleria);
		
		horario = new Horario();
		horario.setId(1);
		horario.setFecha(LocalDate.now());
		horario.setDescripcion("prueba");
		horario.setHora_fin(LocalTime.now().plusHours(2));
		horario.setHora_inicio(LocalTime.now());
		horario.setTrabajador(trabajador);
		
		servicio = new Servicio();
		servicio.setId(1);
		servicio.setLugar("Acuario de Sevilla");
		servicio.setTipocategoria(TipoCategoria.Cristaleria);
		servicio.setEstado(EstadoServicio.Aceptado);
		servicio.setFechainicio(LocalDate.of(2020, 12, 31));
		servicio.setFechafin(LocalDate.of(2021, 01, 12));
		servicio.setTrabajadores(Lists.list(trabajador));
		
		given(this.trabajadorService.findTrabajadorById(any())).willReturn(Optional.of(trabajador));
		given(this.horarioService.findHorarioByTrabajadorName(any())).willReturn(Lists.list(horario));
		given(this.trabajadorService.findTrabajadoresByServicio(any())).willReturn(Lists.list(trabajador));
		given(this.servicioService.findServicioById(any())).willReturn(Optional.of(servicio));
		
		List<Trabajador> trabajadores = new ArrayList<Trabajador>();
		trabajadores.add(trabajador);
		given(this.trabajadorService.findAll()).willReturn(trabajadores);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoTrabajadores() throws Exception{
		mockMvc.perform(get("/trabajadores")).andExpect(status().isOk())
		.andExpect(model().attributeExists("trabajadores"))
		.andExpect(view().name("trabajadores/listadoTrabajadores"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNewTrabajador() throws Exception{
		mockMvc.perform(get("/trabajadores/new")).andExpect(status().isOk()).andExpect(model().attributeExists("trabajador"))
		.andExpect(view().name("trabajadores/editTrabajadores"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSalvarTrabajador() throws Exception {
		mockMvc.perform(post("/trabajadores/save")
						.with(csrf())
						.param("dni", "44444444A")
						.param("nombre", "José Manuel")
						.param("apellidos", "González Rodríguez")
						.param("telefono", "635254643")
						.param("direccion", "Calle Sevilla")
						.param("correo", "jmgr12@gmail.com")
						.param("tipocategoria", "Mantenimiento"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/trabajadores"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSalvarTrabajadorConError() throws Exception {
		mockMvc.perform(post("/trabajadores/save")
						.with(csrf())
						.param("dni", "44444444A")
						.param("nombre", "")
						.param("apellidos", "González Rodríguez")
						.param("telefono", "635254643")
						.param("direccion", "Calle Sevilla")
						.param("correo", "jmgr12@gmail.com")
						.param("tipocategoria", "Mantenimiento"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("trabajador"))
			.andExpect(model().attributeHasFieldErrors("trabajador", "nombre"))
			.andExpect(view().name("trabajadores/editTrabajadores"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testIniciarFormularioActualizacionTrabajador() throws Exception {
		mockMvc.perform(get("/trabajadores/{trabajadorId}/edit", 1)).andExpect(status().isOk())
		.andExpect(model().attributeExists("trabajador"))
		.andExpect(view().name("trabajadores/editTrabajadores"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testActualizarTrabajador() throws Exception {
		mockMvc.perform(post("/trabajadores/{trabajadorId}/update", 1)
						.with(csrf())
						.param("dni", "44444444A")
						.param("nombre", "José Manuel")
						.param("apellidos", "González Rodríguez")
						.param("telefono", "666777888")
						.param("direccion", "Calle Sevilla")
						.param("correo", "jmgr12@gmail.com")
						.param("tipocategoria", "Mantenimiento"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/trabajadores"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testDeleteTrabajador() throws Exception {
		mockMvc.perform(get("/trabajadores/delete/{trabajadorId}", 1))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/trabajadores"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoHorariosTrabajadores() throws Exception{
		mockMvc.perform(get("/trabajadores/horariosTrabajador")
				.param("nombreTrab", "Francisco"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("horarios"))
		.andExpect(model().attributeExists("horariosTrabajador"))
		.andExpect(view().name("horarios/listadoHorarios"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoTrabajadoresDeUnServicio() throws Exception{
		mockMvc.perform(get("/trabajadores/servicio/{servicioId}", 1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("trabajadores"))
		.andExpect(model().attributeExists("servicio"))
		.andExpect(view().name("trabajadores/trabajadoresByServicio"));
	}

}
