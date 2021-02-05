package org.springframework.samples.petclinic.web;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Instalacion;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.InstalacionService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javassist.expr.NewArray;

@Controller
@RequestMapping("/instalaciones")
public class InstalacionController {
	
	@Autowired
	private InstalacionService instalacionService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public String listadoInstalaciones(ModelMap modelMap) {
		String vista ="instalaciones/listadoInstalaciones";
		Iterable<Instalacion> instalaciones = instalacionService.findAll();
		modelMap.addAttribute("instalacion", instalaciones);
		return vista;
	}
	
	@GetMapping(path="/{instalacionId}")
	public String verInstalacion(@PathVariable("instalacionId") int instalacionId, ModelMap modelMap) {
		String vista = "instalaciones/verInstalacion";
		Instalacion instalacion = instalacionService.findInstalacionById(instalacionId).get();
		modelMap.addAttribute("instalacion", instalacion);
		return vista;
	}
	
	@GetMapping(path="/filtrado")
	public String filtradoInstalaciones(String nombreCli, ModelMap modelMap) {
		String view="instalaciones/listadoInstalaciones";
		modelMap.addAttribute("filtrado", nombreCli);
		modelMap.addAttribute("instalaciones", instalacionService.findInstalacionByClienteName(nombreCli));
		return view;
	}
	
	@GetMapping(value = "/misInstalaciones")
	public String listadoInstalacionesPorCliente(ModelMap modelMap) {
		String vista = "instalaciones/listadoInstalaciones";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Cliente cliente = clienteService.findClienteByUsername(auth.getName()).get();
		Iterable<Instalacion> instalaciones = instalacionService.findInstalacionByClienteName(cliente.getNombre());
		modelMap.addAttribute("instalaciones", instalaciones);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearInstalacion(ModelMap modelMap) {
		String view="instalaciones/newInstalacion";
		User user = userService.getLoggedUser();
		if(user.getAuthorities().getAuthority().equalsIgnoreCase("cliente")) {
			Cliente cliente = clienteService.findClienteByUsername(user.getUsername()).get();
			modelMap.addAttribute("cliente", cliente);
			modelMap.addAttribute("instalacion", new Instalacion());
		}else {
			return "exception";
		}
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarInstalacion(@Valid Instalacion instalacion, BindingResult result,ModelMap modelMap) {
		String view="succesful";
		if(result.hasErrors()) {
			modelMap.addAttribute("instalacion", instalacion);
			return "instalaciones/newInstalacion";
		}else {
			if(!clienteService.findClienteById(instalacion.getCliente().getId()).isPresent()) {
				modelMap.addAttribute("instalacion", instalacion);
				modelMap.addAttribute("error", "No existe el cliente que ha selecionado");
				return "instalaciones/newInstalacion";
			} else {
				instalacionService.save(instalacion);
				modelMap.addAttribute("message", "Instalacion añadida");
			}
		}
		return view;
	}
	
	@GetMapping(path="/delete/{instalacionId}")
	public String borrarInstalacion(@PathVariable("instalacionId") Integer instalacionId, ModelMap modelmap) {
		String view = "redirect:/instalaciones";
		Optional<Instalacion> instalacion = instalacionService.findInstalacionById(instalacionId);
		if(instalacion.isPresent()) {
			instalacionService.delete(instalacion.get());
			modelmap.addAttribute("message", "Instalación borrada correctamente");
		}else {
			modelmap.addAttribute("message", "Instalación no encontrada");
		}
		return view;
	}

}
