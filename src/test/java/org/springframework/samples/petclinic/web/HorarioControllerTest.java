package org.springframework.samples.petclinic.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= HorarioController.class,
includeFilters= @ComponentScan.Filter(value = Horario.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class HorarioControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private HorarioService horarioService;
	
	@MockBean
	private TrabajadorService trabajadorService;
	
	@MockBean
	private UserService userService;

	@MockBean
	private EntityManager entityManager;
	
	private Horario horario;
	private Trabajador trabajador;
	
	@BeforeEach
	void setup() throws ParseException {
		User user=new User();
		user.setUsername("francisco");
		user.setPassword("admin");
		
		trabajador = new Trabajador();
		trabajador.setId(1);
		trabajador.setDni("36578363P");
		trabajador.setNombre("Francisco");
		trabajador.setApellidos("García");
		trabajador.setTelefono("668368479");
		trabajador.setDireccion("Calle Santana 12");
		trabajador.setCorreo("frangar@gmail.com");
		trabajador.setTipocategoria(TipoCategoria.Cristaleria);
		trabajador.setUser(user);
		
		
		horario = new Horario();
		horario.setId(1);
		
		horario.setFecha(LocalDate.of(2020, 1, 1));
		horario.setHora_inicio(LocalTime.of(12, 30));
		horario.setHora_fin(LocalTime.of(17,30));
		horario.setDescripcion("Ese es tu horario de hoy");
		horario.setTrabajador(trabajador);
		
		given(this.horarioService.findHorarioById(1)).willReturn(Optional.of(horario));
		given(this.horarioService.findHorarioByTrabajadorName(any())).willReturn(Lists.list(horario));
		given(this.trabajadorService.findTrabajadorById(1)).willReturn(Optional.of(trabajador));
		given(this.trabajadorService.findTrabajadorByUsername(any())).willReturn(Optional.of(trabajador));
		
		List<Horario> horarios = new ArrayList<Horario>();
		horarios.add(horario);
		given(this.horarioService.findAll()).willReturn(Lists.list(horario));
		given(this.entityManager.find(Trabajador.class, 1)).willReturn(trabajador);
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoHorarios() throws Exception{
		mockMvc.perform(get("/horarios"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("horarios"))
			.andExpect(view().name("horarios/listadoHorarios"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoHorariosPorTrabajadorLogueado() throws Exception{
		mockMvc.perform(get("/horarios/misHorarios"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("horarios"))
			.andExpect(view().name("horarios/listadoHorariosPorTrabajador"));
	}
	

	@WithMockUser(value = "spring")
	@Test
	void testListadoHorariosPorTrabajadorId() throws Exception{
		mockMvc.perform(get("/horarios/{trabajadorId}", 1))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("horarios", "trabajador"))
			.andExpect(view().name("horarios/listadoHorariosPorTrabajador"));
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testNewHorario() throws Exception{
		mockMvc.perform(get("/horarios/new/{trabajadorId}", 1)).andExpect(status().isOk()).andExpect(model().attributeExists("horario"))
		.andExpect(view().name("horarios/newHorario"));
	}
	
	
	
	@WithMockUser(value = "spring")
    @Test
    void testSaveHorario() throws Exception {
		mockMvc.perform(post("/horarios/save")
						.with(csrf())
						.param("fecha", "2000/10/03")
						.param("hora_inicio", "14:30")
						.param("hora_fin", "17:30")
						.param("trabajador","1")
						.param("descripcion", "Ese es tu horario de hoy"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("horarios/newHorario"));
	}

	@WithMockUser(value = "spring")
	@Test
	void DeleteHorario() throws Exception{
		mockMvc.perform(get("/horarios/delete/{horarioId}", 1))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/horarios"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/horarios/save")
						.with(csrf())
						.param("fecha", "2000/10/03")
						.param("hora_inicio", "10:30")
						.param("hora_fin", "17:30")
						.param("trabajador","1")
						.param("descripcion", ""))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("horario"))
			.andExpect(model().attributeHasFieldErrors("horario", "descripcion"))
			.andExpect(view().name("horarios/newHorario"));
	}
}
