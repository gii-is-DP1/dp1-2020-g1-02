package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Curriculum;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.Trabajador;
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
		Cliente cliente = new Cliente();
		modelMap.addAttribute("cliente", cliente);
		return vista;
	}
	
	@GetMapping("/newProveedor")
	public String newProveedor(ModelMap modelMap) {
		String vista ="proveedores/newProveedor";
		Proveedor proveedor = new Proveedor();
		modelMap.addAttribute("proveedor", proveedor);
		return vista;
	}
	
	@GetMapping("/newAdministrador")
	public String newAdministrador(ModelMap modelMap) {
		String vista ="administradores/newAdministrador";
		Administrador administrador = new Administrador();
		modelMap.addAttribute("trabajador", administrador);
		return vista;
	}
	
	@GetMapping("/newTrabajador")
	public String newTrabajador(ModelMap modelMap) {
		String vista ="trabajadores/editTrabajadores";
		Trabajador trabajador = new Trabajador();
		modelMap.addAttribute("trabajador", trabajador);
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
	public String changeUserPassword(Locale locale,ModelMap modelMap, 
	  @RequestParam("psw") String password, @RequestParam("oldpass") String oldPassword) {
	    User user = userService.findUser(
	      SecurityContextHolder.getContext().getAuthentication().getName()).get();
	    
		  if (!passwordEncoder.matches(oldPassword, user.getPassword())) { 
			  throw new IllegalAccessError(); 
		  }
		 
	    user.setPassword(passwordEncoder.encode(password));
	    userService.saveUser(user);
	    modelMap.addAttribute("message", "Contraseña actualizada con éxito!");
	    return "redirect:/users/" + user.getUsername();
	}
	
	@PostMapping(path="/saveCliente")
	public String salvarCliente(@Valid Cliente cliente, BindingResult result,ModelMap modelMap) {
		String view="redirect:/";
		if(result.hasErrors()) {
			modelMap.addAttribute("cliente", cliente);
			return "clientes/newCliente";
		}else {
			clienteService.saveCliente(cliente);
			//modelMap.addAttribute("message", "Cliente actualizado!");
		}
		return view;
	}
	
	@PostMapping(path="/saveTrabajador")
	public String salvarTrabajador(@Valid Trabajador trabajador, BindingResult result,ModelMap modelMap) {
		String view="redirect:/";
		if(result.hasErrors()) {
			modelMap.addAttribute("trabajador", trabajador);
			return "users/newTrabajador";
		}else {
			trabajadorService.saveTrabajador(trabajador);
			//modelMap.addAttribute("message", "Trabajador insertado correctamente!");
		}
		return view;
	}
	
	@PostMapping(path="/saveProveedor")
	public String salvarProveedor(@Valid Proveedor proveedor, BindingResult result,ModelMap modelMap) {
		String view="redirect:/";
		if(result.hasErrors()) {
			modelMap.addAttribute("proveedor", proveedor);
			return "users/newProveedor";
		}else {
			proveedorService.saveProveedor(proveedor);
			//modelMap.addAttribute("message", "Cliente actualizado!");
		}
		return view;
	}
	
	@PostMapping(path="/saveAdministrador")
	public String salvarAdministrador(@Valid Administrador trabajador, BindingResult result,ModelMap modelMap) {
		String view="redirect:/";
		if(result.hasErrors()) {
			modelMap.addAttribute("trabajador", trabajador);
			return "users/newAdministrador";
		}else {
			administradorService.saveAdministrador(trabajador);
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