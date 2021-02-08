package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.ClienteForm;
import org.springframework.samples.petclinic.model.Curriculum;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.ProveedorForm;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.TrabajadorForm;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AdministradorService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.CurriculumService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private TrabajadorService trabajadorService;
	@Autowired
	private AdministradorService administradorService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private CurriculumService curriculumService;
	@Autowired
	private ProveedorService proveedorService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public static String EXCEPTION_VIEW = "/exception";
	public static String PERFIL_VIEW = "users/perfilView";
	
	@GetMapping("/new")
	public String nuevoCliente(ModelMap modelMap) {
		String vista ="clientes/newCliente";
		ClienteForm clienteForm = new ClienteForm();
		modelMap.addAttribute("clienteForm", clienteForm);
		return vista;
	}
	
	@GetMapping("/newProveedor")
	public String newProveedor(ModelMap modelMap) {
		String vista ="proveedores/newProveedor";
		ProveedorForm proveedorForm = new ProveedorForm();
		modelMap.addAttribute("proveedorForm", proveedorForm);
		return vista;
	}
	
	@GetMapping("/newAdministrador")
	public String newAdministrador(ModelMap modelMap) {
		String vista ="administradores/newAdministrador";
		TrabajadorForm administradorForm = new TrabajadorForm();
		modelMap.addAttribute("administradorForm", administradorForm);
		return vista;
	}
	
	@GetMapping("/newTrabajador")
	public String newTrabajador(ModelMap modelMap) {
		String vista ="trabajadores/editTrabajadores";
		TrabajadorForm trabajadorForm = new TrabajadorForm();
		modelMap.addAttribute("trabajadorForm", trabajadorForm);
		return vista;
	}
	
	@GetMapping("/newTrabajador/{idCurriculum}")
	public String newTrabajadorCurriculum(@PathVariable("idCurriculum") Integer idCurriculum, ModelMap modelMap) {
		String vista ="trabajadores/editTrabajadores";
		Curriculum curriculum = curriculumService.findCurriculumById(idCurriculum).get();
		Trabajador trabajador = new Trabajador();
		trabajador.setNombre(curriculum.getNombre());
		trabajador.setApellidos(curriculum.getApellidos());
		trabajador.setTelefono(curriculum.getTelefono());
		trabajador.setCorreo(curriculum.getCorreo());
		trabajador.setTipocategoria(curriculum.getTipocategoria());
		modelMap.addAttribute("trabajador", trabajador);
		return vista;
	}
	
	@GetMapping("/editContrasenya")
	public String editContrasenya(Principal principal, ModelMap modelMap) {
		String vista ="users/editContrasenya";
		if(principal == null) {
			modelMap.addAttribute("error", "No has iniciado sesión");
			throw new UsernameNotFoundException("No has iniciado sesión");
		}
		Optional<User> user = this.userService.findUser(principal.getName());
		modelMap.addAttribute("actualPass", user.get().getPassword());
		return vista;
	}
	
	@PostMapping("/updatePassword")
	public String changeUserPassword(Locale locale,RedirectAttributes attributes, 
	  @RequestParam("psw") String password, @RequestParam("cpsw") String cpassword, @RequestParam("oldpass") String oldPassword) {
	    User user = userService.findUser(
	      SecurityContextHolder.getContext().getAuthentication().getName()).get();
	    
		  if (!passwordEncoder.matches(oldPassword, user.getPassword()) || !password.equals(cpassword)) { 
			  throw new IllegalAccessError(); 
		  }
		 
	    user.setPassword(password);
	    userService.saveUser(user);
	    attributes.addAttribute("message", "Contraseña actualizada con éxito!");
	    return "redirect:/users/" + user.getUsername();
	}
	
	@PostMapping(path="/saveCliente")
	public String salvarCliente(@Valid ClienteForm clienteForm, BindingResult result,ModelMap modelMap) {
		String view="redirect:/";
		if(result.hasErrors()) {
			modelMap.addAttribute("clienteForm", clienteForm);
			return "clientes/newCliente";
		}else {

			cliente.getUser().setPassword(passwordEncoder.encode(cliente.getUser().getPassword()));
			clienteService.saveCliente(cliente);

			User newUser = new User();
			newUser.setUsername(clienteForm.getUsername());
			newUser.setPassword(clienteForm.getPassword());
			
			Cliente  newCliente = new Cliente();
			newCliente.setNombre(clienteForm.getNombre());
			newCliente.setApellidos(clienteForm.getApellidos());
			newCliente.setDni(clienteForm.getDni());
			newCliente.setCorreo(clienteForm.getCorreo());
			newCliente.setDireccion(clienteForm.getDireccion());
			newCliente.setTelefono(clienteForm.getTelefono());
			newCliente.setUser(newUser);
			
			clienteService.saveCliente(newCliente);

			//modelMap.addAttribute("message", "Cliente actualizado!");
		}
		return view;
	}
	
	
	@PostMapping(path="/saveProveedor")
	public String salvarProveedor(@Valid ProveedorForm proveedorForm, BindingResult result,ModelMap modelMap) {
		String view="redirect:/";
		if(result.hasErrors()) {
			modelMap.addAttribute("proveedorForm", proveedorForm);
			return "proveedores/newProveedor";
		}else {

			User newUser = new User();
			newUser.setUsername(proveedorForm.getUsername());
			newUser.setPassword(proveedorForm.getPassword());
			
			Proveedor  newProveedor = new Proveedor();
			newProveedor.setName(proveedorForm.getNombre());
			newProveedor.setEmail(proveedorForm.getCorreo());
			newProveedor.setDireccion(proveedorForm.getDireccion());
			newProveedor.setTelefono(proveedorForm.getTelefono());
			newProveedor.setUser(newUser);
			
			proveedorService.saveProveedor(newProveedor);
			//modelMap.addAttribute("message", "Cliente actualizado!");
		}
		return view;
	}
	
	@PostMapping(path="/saveTrabajador")
	public String salvarTrabajador(@Valid TrabajadorForm trabajadorForm, BindingResult result,ModelMap modelMap) {
		String view="redirect:/";
		if(result.hasErrors()) {
			modelMap.addAttribute("trabajadorForm", trabajadorForm);
			return "trabajadores/editTrabajadores";
		}else {

			User newUser = new User();
			newUser.setUsername(trabajadorForm.getUsername());
			newUser.setPassword(trabajadorForm.getPassword());
			
			Trabajador  newTrabajador = new Trabajador();
			newTrabajador.setNombre(trabajadorForm.getNombre());
			newTrabajador.setApellidos(trabajadorForm.getApellidos());
			newTrabajador.setDni(trabajadorForm.getDni());
			newTrabajador.setCorreo(trabajadorForm.getCorreo());
			newTrabajador.setDireccion(trabajadorForm.getDireccion());
			newTrabajador.setTelefono(trabajadorForm.getTelefono());
			newTrabajador.setTipocategoria(trabajadorForm.getTipocategoria());
			newTrabajador.setUser(newUser);
			
			trabajadorService.saveTrabajador(newTrabajador);
			//modelMap.addAttribute("message", "Cliente actualizado!");
		}
		return view;
	}
	
	@PostMapping(path="/saveAdministrador")
	public String salvarAdministrador(@Valid TrabajadorForm administradorForm, BindingResult result, ModelMap modelMap) {
		String view="redirect:/";
		if(result.hasErrors()) {
			modelMap.addAttribute("administradorForm", administradorForm);
			modelMap.addAttribute("result", result);
			return "administradores/newAdministrador";
		}else {

			User newUser = new User();
			newUser.setUsername(administradorForm.getUsername());
			newUser.setPassword(administradorForm.getPassword());
			
			Administrador newAdministrador = new Administrador();
			newAdministrador.setNombre(administradorForm.getNombre());
			newAdministrador.setApellidos(administradorForm.getApellidos());
			newAdministrador.setDni(administradorForm.getDni());
			newAdministrador.setCorreo(administradorForm.getCorreo());
			newAdministrador.setDireccion(administradorForm.getDireccion());
			newAdministrador.setTelefono(administradorForm.getTelefono());
			newAdministrador.setTipocategoria(administradorForm.getTipocategoria());
			newAdministrador.setUser(newUser);
			
			administradorService.saveAdministrador(newAdministrador);

			//modelMap.addAttribute("message", "Cliente actualizado!");
		}
		return view;
	}
	
	@PostMapping(path="/actualizarCliente")
	public String actualizarCliente(@Valid Cliente cliente, BindingResult result,ModelMap modelMap) {
		String view = PERFIL_VIEW;
		User principal = userService.getLoggedUser();
		if(result.hasErrors()) {
			modelMap.addAttribute("cliente", cliente);
		}else {
			if(principal != null && principal.getUsername().equals(cliente.getUser().getUsername())) {
				Cliente clientToUpDate = clienteService.findClienteById(cliente.getId()).get();
				
				clientToUpDate.setCorreo(cliente.getCorreo());
				clientToUpDate.setDireccion(cliente.getDireccion());
				clientToUpDate.setTelefono(cliente.getTelefono());
				
				
				clienteService.actualizarCliente(clientToUpDate);
				
				modelMap.addAttribute("cliente", clientToUpDate);
				modelMap.addAttribute("message", "Perfil actualizaddo con éxito!");
			}else {
				view = EXCEPTION_VIEW;
				modelMap.addAttribute("error", "No tienes permisos para acceder a esta página");
			}
		}
		return view;
	}
	
	@PostMapping(path="/actualizarTrabajador")
	public String actualizarTrabajador(@Valid Trabajador trabajador, BindingResult result,ModelMap modelMap) {
		String view = PERFIL_VIEW;
		User principal = userService.getLoggedUser();
		if(result.hasErrors()) {
			modelMap.addAttribute("trabajador", trabajador);
		}else {
			if(principal != null && principal.getUsername().equals(trabajador.getUser().getUsername())) {
				Trabajador employeeToUpDate = trabajadorService.findTrabajadorById(trabajador.getId()).get();
				
				employeeToUpDate.setCorreo(trabajador.getCorreo());
				employeeToUpDate.setDireccion(trabajador.getDireccion());
				employeeToUpDate.setTelefono(trabajador.getTelefono());
				
				
				trabajadorService.actualizarTrabajador(employeeToUpDate);
				
				modelMap.addAttribute("trabajador", employeeToUpDate);
				modelMap.addAttribute("message", "Perfil actualizaddo con éxito!");
			}else {
				view = EXCEPTION_VIEW;
				modelMap.addAttribute("error", "No tienes permisos para acceder a esta página");
			}
		}
		return view;
	}
	
	@PostMapping(path="/actualizarProveedor")
	public String actualizarProveedor(@Valid Proveedor proveedor, BindingResult result,ModelMap modelMap) {
		String view = PERFIL_VIEW;
		User principal = userService.getLoggedUser();
		if(result.hasErrors()) {
			modelMap.addAttribute("proveedor", proveedor);
		}else {
			if(principal != null && principal.getUsername().equals(proveedor.getUser().getUsername())) {
				Proveedor supplierToUpDate = proveedorService.findProveedorById(proveedor.getId()).get();
		
				supplierToUpDate.setEmail(proveedor.getEmail());
				supplierToUpDate.setDireccion(proveedor.getDireccion());
				supplierToUpDate.setTelefono(proveedor.getTelefono());
				
				
				proveedorService.actualizarProveedor(supplierToUpDate);
				
				modelMap.addAttribute("proveedor", supplierToUpDate);
				modelMap.addAttribute("message", "Perfil actualizado con éxito!");
			}else {
				view = EXCEPTION_VIEW;
				modelMap.addAttribute("error", "No tienes permisos para acceder a esta página");
			}
		}
		return view;
	}
	
	@PostMapping(path="/actualizarAdministrador")
	public String actualizarAdministrador(@Valid Administrador trabajador, BindingResult result,ModelMap modelMap) {
		String view = PERFIL_VIEW;
		User principal = userService.getLoggedUser();
		if(result.hasErrors()) {
			modelMap.addAttribute("trabajador", trabajador);
		}else {
			if(principal != null && principal.getUsername().equals(trabajador.getUser().getUsername())) {
				Administrador adminToUpDate = administradorService.findAdministradorById(trabajador.getId()).get();
				
				adminToUpDate.setCorreo(trabajador.getCorreo());
				adminToUpDate.setDireccion(trabajador.getDireccion());
				adminToUpDate.setTelefono(trabajador.getTelefono());
				
				administradorService.actualizarAdministrador(adminToUpDate);
				
				modelMap.addAttribute("trabajador", adminToUpDate);
				modelMap.addAttribute("message", "Perfil actualizado con éxito!");
			}else {
				view = EXCEPTION_VIEW;
				modelMap.addAttribute("error", "No tienes permisos para acceder a esta página");
			}
		}
		return view;
	}
	
	@GetMapping(path="/{username}")
	public String getUserInfo(@PathVariable("username") String username, ModelMap modelMap) {
		String view= EXCEPTION_VIEW;
		User principal = userService.getLoggedUser();
		if(principal != null && principal.getUsername().equals(username)) {
			view = PERFIL_VIEW;
			switch(principal.getAuthorities().getAuthority()) {
				case "administrador": 
					Administrador administrador = administradorService.findAdministradorByUsername(principal.getUsername()).get();
					modelMap.addAttribute("administrador", administrador);
					break;
				case "trabajador": 
					Trabajador trabajador = trabajadorService.findTrabajadorByUsername(principal.getUsername()).get();
					modelMap.addAttribute("trabajador", trabajador);
					break;
				case "proveedor": 
					Proveedor proveedor = proveedorService.findProveedorByUsername(principal.getUsername()).get();
					modelMap.addAttribute("proveedor", proveedor);
					break;
				case "cliente": 
					Cliente cliente = clienteService.findClienteByUsername(principal.getUsername()).get();
					modelMap.addAttribute("cliente", cliente);
					break;
				default: 
					view= EXCEPTION_VIEW;
					modelMap.addAttribute("error", "Se ha producido un error inesperado");
					break;
			}
		}else{
			modelMap.addAttribute("error", "No tienes permisos para acceder a esta página");
		}return view;
	}
}