package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;

import org.springframework.samples.petclinic.model.TipoCategoria;

import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Valoracion;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.ValoracionService;
import org.springframework.samples.petclinic.service.exceptions.AntesComenzarServicioException;
import org.springframework.samples.petclinic.service.exceptions.ServicioNoAceptadoException;
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

		Map<TipoCategoria, Integer> valoraciones = valoracionService.getMediaValoraciones();

		
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
		if(user.getUsername().equals(v.getServicio().getCliente().getUser().getUsername())) {
			Cliente cliente = clienteService.findClienteByUsername(user.getUsername()).get();
			modelMap.addAttribute("clientes", cliente);
			modelMap.addAttribute("valoracion", v);
		}else {
			modelMap.addAttribute("message", "No tienes permiso para realizar esta acción.");
			return "exception";
		}
		
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarValoracion(@Valid Valoracion valoracion, BindingResult result,ModelMap modelMap) {
		String view="succesful";
		if(result.hasErrors()) {
			modelMap.addAttribute("valoracion", valoracion);
			modelMap.addAttribute("message", "Hay errores en el formulario");
			return "valoraciones/newValoracion";
		}else {
			try {
				valoracionService.comprobarExcepciones(valoracion);
				modelMap.addAttribute("message", "Valoracion añadida! Gracias por tu ayuda a mejorar.");
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
}
