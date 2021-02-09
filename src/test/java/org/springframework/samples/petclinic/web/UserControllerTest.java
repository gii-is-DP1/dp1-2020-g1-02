package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
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
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.ClienteForm;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.ProveedorForm;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.TrabajadorForm;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AdministradorService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.CurriculumService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= UserController.class,
includeFilters= @ComponentScan.Filter(value = User.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	@MockBean
	private AdministradorService adminService;
	@MockBean
	private TrabajadorService trabajadorService;
	@MockBean
	private ClienteService clienteService;
	@MockBean
	private ProveedorService provService;
	@MockBean
	private CurriculumService curriculumService;
	@MockBean
	private PasswordEncoder passEnconder;
	@MockBean
	private EntityManager entityManager;

	private User user;
	private TrabajadorForm admin;
	private ProveedorForm proveedor;
	private ClienteForm cliente;
	private TrabajadorForm trabajador;
	private Administrador admin2;
	private Proveedor proveedor2;
	private Cliente cliente2;
	private Trabajador trabajador2;
	private Authorities auth;
	
	@BeforeEach
	void setup() {
		
		auth = new Authorities();
		user = new User();
		
		proveedor = new ProveedorForm();
		proveedor.setNombre("Carlos Jesus");
		proveedor.setTelefono("666666666");
		proveedor.setCorreo("carvilgar1@alum.es");
		proveedor.setDireccion("C/ Sobressaliente, 10");
		proveedor.setUsername("carvilgar1");
		proveedor.setPassword("aaaaaaaaA1");
		proveedor.setRetypePassword("aaaaaaaaA1");
		
		admin = new TrabajadorForm();
		admin.setNombre("Carlos Jesus");
		admin.setApellidos("Villadiego García");
		admin.setTelefono("666666666");
		admin.setCorreo("carvilgar1@alum.es");
		admin.setDni("21150498C");
		admin.setDireccion("C/ Sobressaliente, 10");
		admin.setTipocategoria(TipoCategoria.Limpieza);
		admin.setUsername("carvilgar1");
		admin.setPassword("aaaaaaaaA1");
		admin.setRetypePassword("aaaaaaaaA1");
		
		cliente = new ClienteForm();
		cliente.setNombre("Carlos Jesus");
		cliente.setApellidos("Villadiego García");
		cliente.setTelefono("666666666");
		cliente.setCorreo("carvilgar1@alum.es");
		cliente.setDni("21150498C");
		cliente.setDireccion("C/ Sobressaliente, 10");
		cliente.setUsername("carvilgar1");
		cliente.setPassword("aaaaaaaaA1");
		cliente.setRetypePassword("aaaaaaaaA1");
		
		trabajador = new TrabajadorForm();
		trabajador.setNombre("Carlos Jesus");
		trabajador.setApellidos("Villadiego García");
		trabajador.setTelefono("666666666");
		trabajador.setCorreo("carvilgar1@alum.es");
		trabajador.setDni("21150498C");
		trabajador.setDireccion("C/ Sobressaliente, 10");
		trabajador.setTipocategoria(TipoCategoria.Limpieza);
		trabajador.setUsername("carvilgar1");
		trabajador.setPassword("aaaaaaaaA1");
		trabajador.setRetypePassword("aaaaaaaaA1");
		
		cliente2 = new Cliente();
		cliente2.setNombre("Carlos Jesus");
		cliente2.setApellidos("Villadiego García");
		cliente2.setTelefono("666666666");
		cliente2.setCorreo("carvilgar1@alum.es");
		cliente2.setDni("21150498C");
		cliente2.setDireccion("C/ Sobressaliente, 10");
		
		proveedor2 = new Proveedor();
		proveedor2.setName("Carlos Jesus");
		proveedor2.setTelefono("666666666");
		proveedor2.setEmail("carvilgar1@alum.es");
		proveedor2.setDireccion("C/ Sobressaliente, 10");
		
		admin2 = new Administrador();
		admin2.setNombre("Carlos Jesus");
		admin2.setApellidos("Villadiego García");
		admin2.setTelefono("666666666");
		admin2.setCorreo("carvilgar1@alum.es");
		admin2.setDni("21150498C");
		admin2.setDireccion("C/ Sobressaliente, 10");
		admin2.setTipocategoria(TipoCategoria.Limpieza);
		
		trabajador2 = new Trabajador();
		trabajador2.setNombre("Carlos Jesus");
		trabajador2.setApellidos("Villadiego García");
		trabajador2.setTelefono("666666666");
		trabajador2.setCorreo("carvilgar1@alum.es");
		trabajador2.setDni("21150498C");
		trabajador2.setDireccion("C/ Sobressaliente, 10");
		trabajador2.setTipocategoria(TipoCategoria.Limpieza);
		
		given(this.userService.getLoggedUser()).willReturn(user);
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNuevoCliente() throws Exception {
		mockMvc.perform(get("/users/new")).andExpect(status().isOk())
		.andExpect(model().attributeExists("clienteForm"))
		.andExpect(view().name("users/newCliente"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNuevoProveedor() throws Exception {
		mockMvc.perform(get("/users/newProveedor")).andExpect(status().isOk())
		.andExpect(model().attributeExists("proveedorForm"))
		.andExpect(view().name("users/newProveedor"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNuevoAdmin() throws Exception {
		mockMvc.perform(get("/users/newAdministrador")).andExpect(status().isOk())
		.andExpect(model().attributeExists("administradorForm"))
		.andExpect(view().name("users/newAdministrador"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNuevoTrabajador() throws Exception {
		mockMvc.perform(get("/users/newTrabajador")).andExpect(status().isOk())
		.andExpect(model().attributeExists("trabajadorForm"))
		.andExpect(view().name("users/editTrabajadores"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSaveCliente() throws Exception {
		mockMvc.perform(post("/users/saveCliente")
						.with(csrf())
						.param("nombre", "Rodolfo")
						.param("apellidos", "Rodriguez")
						.param("telefono", "666555444")
						.param("direccion", "calle patin")
						.param("dni", "55555555K")
						.param("correo", "rodolfor@gmail.com")
						.param("username", "rodo")
						.param("password", "aaaaaaaaA1")
						.param("retypePassword", "aaaaaaaaA1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/"));
		
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSaveClienteConError() throws Exception {
		mockMvc.perform(post("/users/saveCliente")
				.with(csrf())
				.param("nombre", "")
				.param("apellidos", "Rodriguez")
				.param("telefono", "666555444")
				.param("direccion", "calle patin")
				.param("dni", "55555555R")
				.param("correo", "rodolfor@gmail.com")
				.param("username", "rodo")
				.param("password", "aaaaaaaaA1")
				.param("retypePassword", "dadwdawd"))
		.andExpect(model().attributeHasErrors("clienteForm"))
		.andExpect(model().attributeHasFieldErrors("clienteForm", "nombre"))
		.andExpect(status().isOk())
		.andExpect(view().name("users/newCliente"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSaveTrabajador() throws Exception {
		mockMvc.perform(post("/users/saveTrabajador")
						.with(csrf())
						.param("nombre", "David")
						.param("apellidos", "Gutierrez")
						.param("telefono", "666333222")
						.param("direccion", "calle mariposa")
						.param("dni", "44444444A")
						.param("correo", "davidg@gmail.com")
						.param("tipocategoria", "Mantenimiento")
						.param("username", "david")
						.param("password", "daaaAvid1")
						.param("retypePassword", "daaaAvid1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSaveTrabajadorConError() throws Exception {
		mockMvc.perform(post("/users/saveTrabajador")
				.with(csrf())
				.param("nombre", "David")
				.param("apellidos", "")
				.param("telefono", "666333222")
				.param("direccion", "calle mariposa")
				.param("dni", "44444444A")
				.param("correo", "davidg@gmail.com")
				.param("tipocategoria", "Mantenimiento")
				.param("username", "david")
				.param("password", "daaaAvid1")
				.param("retypePassword", "daaaAvid1"))
		.andExpect(model().attributeHasErrors("trabajadorForm"))
		.andExpect(model().attributeHasFieldErrors("trabajadorForm", "apellidos"))
		.andExpect(status().isOk())
		.andExpect(view().name("users/editTrabajadores"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSaveProveedor() throws Exception {
		mockMvc.perform(post("/users/saveProveedor")
						.with(csrf())
						.param("nombre", "Lejias SL")
						.param("telefono", "666555444")
						.param("direccion", "calle rodolfo, n12")
						.param("correo", "lejiassl@gmail.com")
						.param("username", "lejiassl")
						.param("password", "aaaaaaaaA1")
						.param("retypePassword", "aaaaaaaaA1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSaveProveedorConError() throws Exception {
		mockMvc.perform(post("/users/saveProveedor")
				.with(csrf())
				.param("nombre", "Lejias SL")
				.param("telefono", "98")
				.param("direccion", "calle rodolfo, n12")
				.param("email", "lejiassl@gmail.com")
				.param("username", "lejiassl")
				.param("password", "daaaAvid1")
				.param("retypePassword", "daaaAvid1"))
		.andExpect(model().attributeHasErrors("proveedorForm"))
		.andExpect(model().attributeHasFieldErrors("proveedorForm", "telefono"))
		.andExpect(status().isOk())
		.andExpect(view().name("users/newProveedor"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSaveAdministrador() throws Exception {
		mockMvc.perform(post("/users/saveAdministrador")
						.with(csrf())
						.param("nombre", "Wilfredo")
						.param("apellidos", "Garcia")
						.param("telefono", "666777888")
						.param("direccion", "calle macarena")
						.param("dni", "44444444A")
						.param("correo", "wilfredog@gmail.com")
						.param("tipocategoria", "Limpieza")
						.param("username", "wilgar")
						.param("password", "daaaAvid1")
						.param("retypePassword", "daaaAvid1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSaveAdministradorConError() throws Exception {
		mockMvc.perform(post("/users/saveAdministrador")
				.with(csrf())
				.param("nombre", "Wilfredo")
				.param("apellidos", "Garcia")
				.param("telefono", "666777888")
				.param("direccion", "calle macarena")
				.param("dni", "44444444A")
				.param("correo", "")
				.param("tipocategoria", "Limpieza")
				.param("username", "wilgar")
				.param("password", "daaaAvid1")
				.param("retypePassword", "daaaAvid1"))
		.andExpect(model().attributeHasErrors("trabajadorForm"))
		.andExpect(model().attributeHasFieldErrors("trabajadorForm", "correo"))
		.andExpect(status().isOk())
		.andExpect(view().name("users/newAdministrador"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testActualizarCliente() throws Exception {
		auth.setId(1);
		auth.setAuthority("cliente");
		user.setUsername("rodo");
		user.setPassword("daaaAvid1");
		auth.setUser(user);
		cliente2.setUser(user);
		given(entityManager.find(User.class, "rodo")).willReturn(user);
		given(this.clienteService.findClienteById(any())).willReturn(Optional.of(cliente2));
		mockMvc.perform(post("/users/actualizarCliente")
				.with(csrf())
				.param("nombre", "Rodolfo")
				.param("apellidos", "Rodriguez")
				.param("telefono", "666555444")
				.param("direccion", "calle patin")
				.param("dni", "55555555K")
				.param("correo", "rodolfor@gmail.com")
				.param("user", "rodo"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("users/perfilView"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testActualizarProveedor() throws Exception {
		auth.setId(1);
		auth.setAuthority("proveedor");
		user.setUsername("lejiassl");
		user.setPassword("123asd");
		auth.setUser(user);
		proveedor2.setUser(user);
		given(this.provService.findProveedorById(any())).willReturn(Optional.of(proveedor2));
		given(entityManager.find(User.class, "lejiassl")).willReturn(user);
		mockMvc.perform(post("/users/actualizarProveedor")
				.with(csrf())
				.param("name", "Lejias SL")
				.param("telefono", "666777888")
				.param("direccion", "calle rodolfo, n12")
				.param("email", "lejiassl@gmail.com")
				.param("user", "lejiassl")
				)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("users/perfilView"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testActualizarAdministrador() throws Exception {
		auth.setId(1);
		auth.setAuthority("administrador");
		user.setUsername("wilgar");
		user.setPassword("wilgar");
		auth.setUser(user);
		admin2.setUser(user);
		given(this.adminService.findAdministradorById(any())).willReturn(Optional.of(admin2));
		given(entityManager.find(User.class, "wilgar")).willReturn(user);
		mockMvc.perform(post("/users/actualizarAdministrador")
				.with(csrf())
				.param("nombre", "Wilfredo")
				.param("apellidos", "Garcia")
				.param("telefono", "666777888")
				.param("direccion", "calle macarena")
				.param("dni", "44444444A")
				.param("correo", "wilfredog@gmail.com")
				.param("tipocategoria", "Limpieza")
				.param("user", "wilgar")
				)
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("users/perfilView"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testActualizarTrabajador() throws Exception {
		auth.setId(1);
		auth.setAuthority("trabajador");
		user.setUsername("davidg");
		user.setPassword("davidg");
		auth.setUser(user);
		trabajador2.setUser(user);
		given(this.trabajadorService.findTrabajadorById(any())).willReturn(Optional.of(trabajador2));
		given(entityManager.find(User.class, "davidg")).willReturn(user);
		mockMvc.perform(post("/users/actualizarTrabajador")
				.with(csrf())
				.param("nombre", "David")
				.param("apellidos", "Gutierrez")
				.param("telefono", "666333222")
				.param("direccion", "calle mariposa")
				.param("dni", "66666666D")
				.param("correo", "davidg@gmail.com")
				.param("tipocategoria", "Mantenimiento")
				.param("user", "davidg")
				)
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("users/perfilView"));
	}
	
}