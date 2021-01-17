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
import org.springframework.samples.petclinic.service.ContratoServicioService;
import org.springframework.samples.petclinic.service.ReclamacionService;
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
	@MockBean
	private ContratoServicioService contratoServicioService;
	@MockBean
	private ReclamacionService reclamacionService;
	
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
		admin.setDireccion("Calle Huertas  31");
		admin.setCorreo("carlosr@gmail.com");
		admin.setTipocategoria(TipoCategoria.Limpieza);
		
		given(this.administradorService.findAdministradorById(1)).willReturn(Optional.of(admin));
		
		List<Administrador> admins = new ArrayList<Administrador>();
		admins.add(admin);
		given(this.administradorService.findAll()).willReturn(admins);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoAdministradores() throws Exception{
		mockMvc.perform(get("/administradores")).andExpect(status().isOk())
		.andExpect(model().attributeExists("administrador"))
		.andExpect(view().name("administradores/listadoAdmin"));
	}
}
