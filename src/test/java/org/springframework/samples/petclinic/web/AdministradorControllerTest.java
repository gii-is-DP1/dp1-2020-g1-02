package org.springframework.samples.petclinic.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
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
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.service.AdministradorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= AdministradorController.class,
			includeFilters= @ComponentScan.Filter(value = AdministradorValidator.class, type = FilterType.ASSIGNABLE_TYPE ),
			excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
			excludeAutoConfiguration= SecurityConfiguration.class)

public class AdministradorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdministradorService administradorService;
	
	private Administrador admin;
	
	@BeforeEach
	void setup() {
		//Preparacion del administrador
		admin = new Administrador();
		admin.setId(1);
		admin.setDni("25615783Q");
		admin.setNombre("Carlos");
		admin.setApellidos("Ramírez");
		admin.setTelefono("628157278");
		admin.setCorreo("carlosr@gmail.com");
		admin.setTipocategoria(TipoCategoria.Limpieza);
		
		given(this.administradorService.findAdministradorById(1)).willReturn(Optional.of(admin));
		
		List<Administrador> admins = new ArrayList<Administrador>();
		admins.add(admin);
		given(this.administradorService.findAll()).willReturn(admins);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testEditAdministrador() throws Exception{
		mockMvc.perform(get("/administradores/new")).andExpect(status().isOk()).andExpect(model().attributeExists("admin"))
		.andExpect(view().name("administradores/editAdmin"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateslotgameForm() throws Exception {
		mockMvc.perform(get("/delete/{adminId}", 1)).andExpect(status().isOk())
				.andExpect(model().attributeExists("admin"))
				.andExpect(model().attribute("admin", hasProperty("nombre", is("Pablo"))))
				.andExpect(model().attribute("admin", hasProperty("telefono", is("633444555"))))
				.andExpect(model().attribute("admin", hasProperty("dni", is("53985965D"))))
				.andExpect(model().attribute("admin", hasProperty("apellidos", is("Gonzalez"))))
				.andExpect(model().attribute("admin", hasProperty("correo", is("pablog@gmail.com"))))
				.andExpect(model().attribute("admin", hasProperty("tipoCategoria", is(TipoCategoria.Limpieza))))
				.andExpect(view().name("administradores/listadoAdmin"));
	}
	
//	@WithMockUser(value = "spring")
//    @Test
//    void testAñadirAdministrador() throws Exception {
//		mockMvc.perform(post("/administradores/save").param("admin", "José")
//						.param("apellidos", "Bejarano")
//						.param("telefono", "645746278")
//						.param("dni", "84947824F"))
//			.andExpect(status().is2xxSuccessful())
//			.andExpect(view().name("administradores/listadoAdmin"));
//	}

}
