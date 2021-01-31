package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AdministradorService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	private ProveedorService proveedorService;
	
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
	
	@PostMapping(path="/saveCliente")
	public String salvarCliente(@Valid Cliente cliente, BindingResult result,ModelMap modelMap) {
		String view="redirect:/";
		if(result.hasErrors()) {
			modelMap.addAttribute("cliente", cliente);
			return "users/new";
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
		String view="redirect:/";
		if(result.hasErrors()) {
			modelMap.addAttribute("cliente", cliente);
			return "users/new";
		}else {
			Cliente clientToUpDate = clienteService.findClienteById(cliente.getId()).get();
			
			clientToUpDate.setNombre(cliente.getNombre());
			clientToUpDate.setApellidos(cliente.getApellidos());
			clientToUpDate.setCorreo(cliente.getCorreo());
			clientToUpDate.setDireccion(cliente.getDireccion());
			clientToUpDate.setTelefono(cliente.getTelefono());
			
			
			clienteService.actualizarCliente(clientToUpDate);
			//modelMap.addAttribute("message", "Cliente actualizado!");
		}
		return view;
	}
	
	@PostMapping(path="/actualizarTrabajador")
	public String actualizarTrabajador(@Valid Trabajador trabajador, BindingResult result,ModelMap modelMap) {
		String view="redirect:/";
		if(result.hasErrors()) {
			modelMap.addAttribute("trabajador", trabajador);
			return "users/newTrabajador";
		}else {
			Trabajador employeeToUpDate = trabajadorService.findTrabajadorById(trabajador.getId()).get();
			
			employeeToUpDate.setNombre(trabajador.getNombre());
			employeeToUpDate.setApellidos(trabajador.getApellidos());
			employeeToUpDate.setCorreo(trabajador.getCorreo());
			employeeToUpDate.setDireccion(trabajador.getDireccion());
			employeeToUpDate.setTelefono(trabajador.getTelefono());
			
			
			trabajadorService.actualizarTrabajador(employeeToUpDate);
		}
		return view;
	}
	
	@PostMapping(path="/actualizarProveedor")
	public String actualizarProveedor(@Valid Proveedor proveedor, BindingResult result,ModelMap modelMap) {
		String view="redirect:/";
		if(result.hasErrors()) {
			modelMap.addAttribute("proveedor", proveedor);
			return "users/newProveedor";
		}else {
			Proveedor supplierToUpDate = proveedorService.findProveedorById(proveedor.getId()).get();
	
			supplierToUpDate.setEmail(proveedor.getEmail());
			supplierToUpDate.setDireccion(proveedor.getDireccion());
			supplierToUpDate.setTelefono(proveedor.getTelefono());
			
			
			proveedorService.actualizarProveedor(supplierToUpDate);
			//modelMap.addAttribute("message", "Cliente actualizado!");
		}
		return view;
	}
	
	@PostMapping(path="/actualizarAdministrador")
	public String actualizarAdministrador(@Valid Administrador trabajador, BindingResult result,ModelMap modelMap) {
		String view="redirect:/";
		if(result.hasErrors()) {
			modelMap.addAttribute("trabajador", trabajador);
			return "users/carvilgar1";
		}else {
			Administrador adminToUpDate = administradorService.findAdministradorById(trabajador.getId()).get();
			
			//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			//String password = trabajador.getUser().getPassword();
			//String encodedPassword = passwordEncoder.encode(password);
			
			adminToUpDate.setNombre(trabajador.getNombre());
			adminToUpDate.setApellidos(trabajador.getApellidos());
			adminToUpDate.setCorreo(trabajador.getCorreo());
			adminToUpDate.setDireccion(trabajador.getDireccion());
			adminToUpDate.setTelefono(trabajador.getTelefono());
			//adminToUpDate.getUser().setPassword(encodedPassword);
			administradorService.actualizarAdministrador(adminToUpDate);
		}
		return view;
	}
	
	@GetMapping(path="/{username}")
	public String getUserInfo(@PathVariable("username") String username, ModelMap modelMap) {
		String view="error";
		User principal = userService.getLoggedUser();
		if(principal != null && principal.getUsername().equals(username)) {
			switch(principal.getAuthorities().getAuthority()) {
				case "administrador": 
					view ="users/perfilView";
					Administrador administrador = administradorService.findAdministradorByUsername(principal.getUsername()).get();
					modelMap.addAttribute("administrador", administrador);
					modelMap.addAttribute("rol", "administrador");
					break;
				case "trabajador": 
					view = "users/perfilView";
					Trabajador trabajador = trabajadorService.findTrabajadorByUsername(principal.getUsername()).get();
					modelMap.addAttribute("trabajador", trabajador);
					modelMap.addAttribute("rol", "trabajador");
					break;
				case "proveedor": 
					view ="users/perfilView";
					Proveedor proveedor = proveedorService.findProveedorByUsername(principal.getUsername()).get();
					modelMap.addAttribute("proveedor", proveedor);
					modelMap.addAttribute("rol", "proveedor");
					break;
				case "cliente": 
					view ="users/perfilView";
					Cliente cliente = clienteService.findClienteByUsername(principal.getUsername()).get();
					modelMap.addAttribute("cliente", cliente);
					modelMap.addAttribute("rol", "cliente");
					break;
				default: break;
			}
		}return view;
	}
}
