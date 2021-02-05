package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyInt;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AdministradorService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
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

	private User user;
	private Administrador admin;
	private Proveedor proveedor;
	private Cliente cliente;
	private Trabajador trabajador;
	private Authorities auth;
	
	@BeforeEach
	void setup() {
		
		auth = new Authorities();
		user = new User();
		
		proveedor = new Proveedor();
		proveedor.setId(1);
		proveedor.setName("Lejias SL");
		proveedor.setTelefono("666555444");
		proveedor.setEmail("lejiassl@gmail.com");
		proveedor.setDireccion("calle rodolfo, n12");
		
		admin = new Administrador();
		admin.setId(1);
		admin.setNombre("Wilfredo");
		admin.setApellidos("Garcia");
		admin.setDni("44444444A");
		admin.setCorreo("wilfredog@gmail.com");
		admin.setDireccion("calle macarena");
		admin.setTelefono("666777888");
		admin.setTipocategoria(TipoCategoria.Limpieza);
		
		cliente = new Cliente();
		cliente.setId(1);
		cliente.setNombre("Rodolfo");
		cliente.setApellidos("Rodriguez");
		cliente.setDni("55555555R");
		cliente.setCorreo("rodolfor@gmail.com");
		cliente.setDireccion("calle patin");
		cliente.setTelefono("666555444");
		
		trabajador = new Trabajador();
		trabajador.setId(1);
		trabajador.setNombre("David");
		trabajador.setApellidos("Gutierrez");
		trabajador.setDni("66666666D");
		trabajador.setCorreo("davidg@gmail.com");
		trabajador.setDireccion("calle mariposa");
		trabajador.setTelefono("666333222");
		trabajador.setTipocategoria(TipoCategoria.Limpieza);
		
		given(this.userService.getLoggedUser()).willReturn(user);
		given(this.clienteService.findClienteById(any())).willReturn(Optional.of(cliente));
		given(this.trabajadorService.findTrabajadorById(anyInt())).willReturn(Optional.of(trabajador));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNuevoCliente() throws Exception {
		mockMvc.perform(get("/users/new")).andExpect(status().isOk())
		.andExpect(model().attributeExists("cliente"))
		.andExpect(view().name("clientes/newCliente"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNuevoProveedor() throws Exception {
		mockMvc.perform(get("/users/newProveedor")).andExpect(status().isOk())
		.andExpect(model().attributeExists("proveedor"))
		.andExpect(view().name("proveedores/newProveedor"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNuevoAdmin() throws Exception {
		mockMvc.perform(get("/users/newAdministrador")).andExpect(status().isOk())
		.andExpect(model().attributeExists("trabajador"))
		.andExpect(view().name("administradores/newAdministrador"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNuevoTrabajador() throws Exception {
		mockMvc.perform(get("/users/newTrabajador")).andExpect(status().isOk())
		.andExpect(model().attributeExists("trabajador"))
		.andExpect(view().name("trabajadores/editTrabajadores"));
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
						.param("dni", "55555555R")
						.param("correo", "rodolfor@gmail.com")
						.param("user.username", "rodo")
						.param("user.password", "rodo"))
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
				.param("user.username", "rodo")
				.param("user.password", "123asd"))
		.andExpect(model().attributeHasErrors("cliente"))
		.andExpect(model().attributeHasFieldErrors("cliente", "nombre"))
		.andExpect(status().isOk())
		.andExpect(view().name("clientes/newCliente"));
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
						.param("dni", "66666666D")
						.param("correo", "davidg@gmail.com")
						.param("tipocategoria", "Mantenimiento")
						.param("user.username", "david")
						.param("user.password", "david"))
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
				.param("dni", "66666666D")
				.param("correo", "davidg@gmail.com")
				.param("tipocategoria", "Mantenimiento")
				.param("user.username", "david")
				.param("user.password", "david"))
		.andExpect(model().attributeHasErrors("trabajador"))
		.andExpect(model().attributeHasFieldErrors("trabajador", "apellidos"))
		.andExpect(status().isOk())
		.andExpect(view().name("users/newTrabajador"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSaveProveedor() throws Exception {
		mockMvc.perform(post("/users/saveProveedor")
						.with(csrf())
						.param("nombre", "Lejias SL")
						.param("telefono", "666555444")
						.param("direccion", "calle rodolfo, n12")
						.param("email", "lejiassl@gmail.com")
						.param("user.username", "lejiassl")
						.param("user.password", "123asd"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testSaveProveedorConError() throws Exception {
		mockMvc.perform(post("/users/saveProveedor")
				.with(csrf())
				.param("nombre", "Lejias SL")
				.param("telefono", "")
				.param("direccion", "calle rodolfo, n12")
				.param("email", "lejiassl@gmail.com")
				.param("user.username", "lejiassl")
				.param("user.password", "123asd"))
		.andExpect(model().attributeHasErrors("proveedor"))
		.andExpect(model().attributeHasFieldErrors("proveedor", "telefono"))
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
						.param("user.username", "wilgar")
						.param("user.password", "wilgar"))
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
				.param("user.username", "wilgar")
				.param("user.password", "wilgar"))
		.andExpect(model().attributeHasErrors("administrador"))
		.andExpect(model().attributeHasFieldErrors("administrador", "correo"))
		.andExpect(status().isOk())
		.andExpect(view().name("users/newAdministrador"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testActualizarCliente() throws Exception {
		auth.setId(1);
		auth.setAuthority("cliente");
		user.setUsername("rodo");
		user.setPassword("rodo");
		auth.setUser(user);
		cliente.setUser(user);
		given(this.clienteService.findClienteById(any())).willReturn(Optional.of(cliente));
		mockMvc.perform(post("/users/actualizarCliente")
				.with(csrf())
				.param("nombre", "Rodolfo")
				.param("apellidos", "Rodriguez")
				.param("telefono", "666555444")
				.param("direccion", "calle patin")
				.param("dni", "55555555R")
				.param("correo", "rodolfor@gmail.com")
				.param("user.username", "rodo")
				)
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/users/rodo"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testActualizarTrabajador() throws Exception {
		auth.setId(1);
		auth.setAuthority("trabajador");
		user.setUsername("davidg");
		user.setPassword("davidg");
		auth.setUser(user);
		trabajador.setUser(user);
		given(this.trabajadorService.findTrabajadorById(any())).willReturn(Optional.of(trabajador));
		mockMvc.perform(post("/users/actualizarTrabajador")
				.with(csrf())
				.param("nombre", "David")
				.param("apellidos", "Gutierrez")
				.param("telefono", "666333222")
				.param("direccion", "calle mariposa")
				.param("dni", "66666666D")
				.param("correo", "davidg@gmail.com")
				.param("tipocategoria", "Mantenimiento")
				.param("user.username", "davidg")
				)
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/users/davidg"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testActualizarProveedor() throws Exception {
		auth.setId(1);
		auth.setAuthority("proveedor");
		user.setUsername("lejiassl");
		user.setPassword("123asd");
		auth.setUser(user);
		proveedor.setUser(user);
		given(this.provService.findProveedorById(any())).willReturn(Optional.of(proveedor));
		mockMvc.perform(post("/users/actualizarProveedor")
				.with(csrf())
				.param("name", "Lejias SL")
				.param("telefono", "666777888")
				.param("direccion", "calle rodolfo, n12")
				.param("email", "lejiassl@gmail.com")
				.param("user.username", "lejiassl")
				)
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/users/lejiassl"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testActualizarAdministrador() throws Exception {
		auth.setId(1);
		auth.setAuthority("administrador");
		user.setUsername("wilgar");
		user.setPassword("wilgar");
		auth.setUser(user);
		admin.setUser(user);
		given(this.adminService.findAdministradorById(any())).willReturn(Optional.of(admin));
		mockMvc.perform(post("/users/actualizarAdministrador")
				.with(csrf())
				.param("nombre", "Wilfredo")
				.param("apellidos", "Garcia")
				.param("telefono", "666777888")
				.param("direccion", "calle macarena")
				.param("dni", "44444444A")
				.param("correo", "wilfredog@gmail.com")
				.param("tipocategoria", "Limpieza")
				.param("user.username", "wilgar")
				)
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/users/wilgar"));
	}
	
}