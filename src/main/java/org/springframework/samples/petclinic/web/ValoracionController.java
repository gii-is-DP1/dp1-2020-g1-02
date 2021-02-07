package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Valoracion;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.ValoracionService;
import org.springframework.samples.petclinic.service.exceptions.AntesComenzarServicioException;
import org.springframework.samples.petclinic.service.exceptions.ServicioNoAceptadoException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/valoraciones")
public class ValoracionController {
	
	@Autowired
	private ValoracionService valoracionService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ServicioService servicioService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public String listadoValoraciones(ModelMap modelMap) {
		String vista ="valoraciones/listadoValoraciones";
		Iterable<Valoracion> valoraciones = valoracionService.findAll();
		modelMap.addAttribute("valoraciones", valoraciones);
		return vista;
	}
	
	@GetMapping(path="/{valoracionId}")
	public String verValoracion(@PathVariable("valoracionId") int valoracionId, ModelMap modelMap) {
		String vista = "valoraciones/verValoracion";

		Valoracion valoracion = valoracionService.findValoracionById(valoracionId).get();
		modelMap.addAttribute("valoracion", valoracion);
		return vista;
	}
	
	@GetMapping(path="/filtrado")
	public String filtradoValoracion(String nombreCli, ModelMap modelMap) {
		String view="valoraciones/listadoValoraciones";
		modelMap.addAttribute("filtrado", nombreCli);
		modelMap.addAttribute("valoraciones", valoracionService.findValoracionByClienteName(nombreCli));
		return view;
	}
	
	@GetMapping(value = "/misValoraciones")
	public String listadoValoracionesPorCliente(ModelMap modelMap) {
		String vista = "valoraciones/listadoValoraciones";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Cliente cliente = clienteService.findClienteByUsername(auth.getName()).get();
		Iterable<Valoracion> valoraciones = valoracionService.findValoracionByClienteName(cliente.getNombre());
		modelMap.addAttribute("valoraciones", valoraciones);
		return vista;
	}
	
	@GetMapping(path="/new/{oId}")
	public String crearValoracion(@PathVariable("oId") Integer oId,ModelMap modelMap) {
		String view="valoraciones/newValoracion";
		Valoracion v = new Valoracion();
		v.setServicio(servicioService.findServicioById(oId).get());
		v.setFecha(LocalDate.now());
		User user = userService.getLoggedUser();
		if(user.getAuthorities().getAuthority().equalsIgnoreCase("cliente")) {
			Cliente cliente = clienteService.findClienteByUsername(user.getUsername()).get();
			modelMap.addAttribute("clientes", cliente);
			modelMap.addAttribute("valoracion", v);
		}else {
			return "exception";
		}
		
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarValoracion(@Valid Valoracion valoracion, BindingResult result,ModelMap modelMap) {
		String view="redirect:/valoraciones/misValoraciones";
		if(result.hasErrors()) {
			modelMap.addAttribute("valoracion", valoracion);
			modelMap.addAttribute("message", "Hay errores en el formulario");
			return "valoraciones/newValoracion";
		}else {
			try {
				valoracionService.comprobarExcepciones(valoracion);
				modelMap.addAttribute("message", "Valoracion añadida!");
			} catch(ServicioNoAceptadoException e) {
				modelMap.addAttribute("valoracion", valoracion);
				modelMap.addAttribute("message", "No se puede añadir una valoración a un servicio no aceptado");
				return "valoraciones/newValoracion";
			} catch(AntesComenzarServicioException e) {
				modelMap.addAttribute("valoracion", valoracion);
				modelMap.addAttribute("message", "No se puede añadir una valoración a un servicio que no ha comenzado");
				return "valoraciones/newValoracion";
			}
		}
		return view;
	}
	
	@GetMapping(path="/delete/{valoracionId}")
	public String borrarValoracion(@PathVariable("valoracionId") Integer valoracionId, ModelMap modelmap) {
		String view = "redirect:/reclamaciones";
		Optional<Valoracion> valoracion = valoracionService.findValoracionById(valoracionId);
		valoracionService.delete(valoracion.get());
		modelmap.addAttribute("message", "Valoración borrada correctamente");
		return view;
	}

}
