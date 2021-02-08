package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= AdministradorController.class,
			includeFilters= @ComponentScan.Filter(value = AdministradorValidator.class, type = FilterType.ASSIGNABLE_TYPE ),
			excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
			excludeAutoConfiguration= SecurityConfiguration.class)

public class AdministradorControllerTest {
	
	public static final Map<Integer, String> letrasDNI = new HashMap<Integer, String>() {{
		put( 0,"T");put( 1,"R" ); put( 2,"W" );
		put( 3,"A" );put( 4,"G" );put( 5,"M" ); 
		put( 6,"Y" );put( 7,"F" );put( 8,"P" ); 
		put( 9,"D" );put( 10,"X" );put( 11,"B" ); 
		put( 12,"N" );put( 13,"J" );put( 14,"Z" );
		put( 15,"S" );put( 16,"Q" );put( 17,"V" );
		put( 18,"H" );put( 19,"L" );put( 20,"C" ); 
		put( 21,"K" ); put( 22,"E" );}}; 
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdministradorService administradorService;
	
	@MockBean
	private ContratoServicioService contratoServicioService;
	
	@MockBean
	private ReclamacionService reclamacionService;
	
	@MockBean
	private TrabajadorService trabajadorService;
	
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
	void testEditAdministrador() throws Exception{
		mockMvc.perform(get("/administradores/new")).andExpect(status().isOk()).andExpect(model().attributeExists("administrador"))
		.andExpect(view().name("administradores/newAdministrador"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoReclamaciones() throws Exception{
		mockMvc.perform(get("/administradores/reclamaciones")).andExpect(status().isOk())
		.andExpect(model().attributeExists("reclamacion"))
		.andExpect(view().name("administradores/listadoReclamaciones"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListaMorosos() throws Exception{
		mockMvc.perform(get("/administradores/morosos")).andExpect(status().isOk())
		.andExpect(model().attributeExists("morosos"))
		.andExpect(view().name("administradores/listadoMorosos"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoAdministradores() throws Exception{
		mockMvc.perform(get("/administradores")).andExpect(status().isOk())
		.andExpect(model().attributeExists("administrador"))
		.andExpect(view().name("administradores/listadoAdmin"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void DeleteAdministrador() throws Exception{
		mockMvc.perform(get("/administradores/delete/{adminId}", 1))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/administradores"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/administradores/save")
						.with(csrf())
						.param("dni", "47390692C")
						.param("nombre", "José Manuel")
						.param("apellidos", "González Rodríguez")
						.param("telefono", "635254643")
						.param("direccion", "Calle Sevilla")
						.param("correo", "jmgr12@gmail.com")
						.param("tipocategoria", "Mantenimiento"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/administradores"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/administradores/save")
						.with(csrf())
						.param("dni", "34574634D")
						.param("nombre", "")
						.param("apellidos", "González Rodríguez")
						.param("telefono", "635254643")
						.param("direccion", "Calle Sevilla")
						.param("correo", "jmgr12@gmail.com")
						.param("tipocategoria", "Mantenimiento"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("administrador"))
			.andExpect(model().attributeHasFieldErrors("administrador", "nombre"))
			.andExpect(view().name("administradores/editAdministradores"));
	}
}
