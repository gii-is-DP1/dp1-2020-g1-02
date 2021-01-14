package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.AdministradorService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
		String vista ="administradores/editAdministrador";
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
}
