package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.samples.petclinic.service.InstalacionService;
import org.springframework.samples.petclinic.service.RegistroHorasService;
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
	private RegistroHorasService registroHorasService;
	
	@MockBean
	private HorarioService horarioService;
	
	@MockBean
	private InstalacionService instalacionService;
	
	private Trabajador trabajador;
	
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
		
		given(this.trabajadorService.findTrabajadorById(1)).willReturn(Optional.of(trabajador));
		
		List<Trabajador> trabajadores = new ArrayList<Trabajador>();
		trabajadores.add(trabajador);
		given(this.trabajadorService.findAll()).willReturn(trabajadores);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNewTrabajador() throws Exception{
		mockMvc.perform(get("/trabajadores/new")).andExpect(status().isOk()).andExpect(model().attributeExists("trabajador"))
		.andExpect(view().name("trabajadores/editTrabajadores"));
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
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/trabajadores/save")
						.with(csrf())
						.param("dni", "35466536D")
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
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/trabajadores/save")
						.with(csrf())
						.param("dni", "23523464S")
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

}
