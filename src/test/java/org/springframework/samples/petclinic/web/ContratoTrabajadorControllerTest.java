package org.springframework.samples.petclinic.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
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

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.ContratoServicio;
import org.springframework.samples.petclinic.model.ContratoTrabajador;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Presupuesto;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.TipoPresupuesto;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.ContratoTrabajadorService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= ContratoTrabajadorController.class,
			includeFilters= @ComponentScan.Filter(value = ContratoValidator.class, type = FilterType.ASSIGNABLE_TYPE ),
			excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
			excludeAutoConfiguration= SecurityConfiguration.class)

public class ContratoTrabajadorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TrabajadorService trabajadorService;
	@MockBean
	private ContratoTrabajadorService contratoTrabajadorService;
	@MockBean
	private EntityManager entityManager;
	
	private ContratoTrabajador cT;
	private Trabajador trab;
	

	@BeforeEach
	void setup() {
		//Preparacion del contratoTrabajador
		trab = new Trabajador();
		trab.setId(1);
		trab.setNombre("Josema");
		trab.setApellidos("Martínez Sánchez");
		trab.setDni("12367357J");
		trab.setCorreo("jms@gmail.com");
		trab.setTelefono("678458367");
		trab.setDireccion("Calle Huelva");
		trab.setTipocategoria(TipoCategoria.Cristaleria);
		
		
		cT = new ContratoTrabajador();
		cT.setId(1);
		cT.setFechainicial(LocalDate.of(2019, 10,10));
		cT.setFechafinal(LocalDate.of(2020, 11, 07));
		cT.setSueldo(18.17);
		cT.setTrabajador(trab);
		
		
		//given(this.contratoServicioService.findAll()).willReturn(Optional.of(cS));
		given(this.contratoTrabajadorService.findContratoTrabajadorById(1)).willReturn(Optional.of(cT));
		given(this.trabajadorService.findTrabajadorById(1)).willReturn(Optional.of(trab));
		given(this.entityManager.find(ContratoTrabajador.class, 1)).willReturn(cT);
		given(this.entityManager.find(Trabajador.class, 1)).willReturn(trab);
		
		given(this.trabajadorService.findTrabajadorByUsername(any())).willReturn(Optional.of(trab));
		//given(this.userService.getLoggedUser()).willReturn(user);
//		((BDDMockito) when(this.user.getAuthorities().getAuthority().equalsIgnoreCase("proveedor"))).willReturn(true);
		
		
		
		
		List<ContratoTrabajador> cTrabs = new ArrayList<ContratoTrabajador>();
		cTrabs.add(cT);
		given(this.contratoTrabajadorService.findAll()).willReturn(cTrabs);
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoContratoTrabajadores() throws Exception{
		mockMvc.perform(get("/contratosTrabajadores"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("contratosTrabajadores"))
		.andExpect(view().name("contratosTrabajadores/listadoContratosTrabajadores"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNewContratoTrabajador() throws Exception{
		mockMvc.perform(get("/contratosTrabajadores/{tId}/new", 1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("contratoTrabajador"))
		.andExpect(model().attribute("contratoTrabajador", hasProperty("trabajador", is(trab))))
		.andExpect(view().name("contratosTrabajadores/editContratoTrabajador"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/contratosTrabajadores/save")
						.with(csrf())
						.param("fechainicial", "2020/11/17")
						.param("fechafinal", "2020/12/19")
						.param("sueldo", "20.8")
						.param("trabajador", "1"))
			.andExpect(status().isOk())
			.andExpect(view().name("redirect:/contratosTrabajadores/1"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/contratosTrabajadores/save")
						.with(csrf())
						.param("fechainicial", "2020/11/17")
						.param("fechafinal", "2020/12/19")
						.param("sueldo", "")
						.param("trabajador", "1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("contratoTrabajador"))
			.andExpect(model().attributeHasFieldErrors("contratoTrabajador", "sueldo"))
			.andExpect(view().name("contratosTrabajadores/editContratoTrabajador"));
	}

}
