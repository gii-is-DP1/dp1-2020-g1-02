package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/servicios")
public class ServicioController {
	
	@Autowired
	private ServicioService servicioService;
	
	@GetMapping()
	public String listadoServicios(ModelMap modelMap) {
		String vista = "servicios/listadoServicios";
		Iterable<Servicio> servicios = servicioService.findAll();
		modelMap.addAttribute("servicios", servicios);
		return vista;
	}
	
	@GetMapping(path="/edit")
	public String editaServicio(ModelMap modelMap) {
		String view="servicios/editServicio";
		modelMap.addAttribute("servicio", new Servicio());
		return view;
	}
	
	@GetMapping(path="/new")
	public String crearServicio(ModelMap modelMap) {
		String view="servicios/editServicio";
		modelMap.addAttribute("servicio", new Servicio());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarServicio(@Valid Servicio servicio, BindingResult result, ModelMap modelMap) {
		String view="redirect:/servicios";
		if(result.hasErrors()) {
			modelMap.addAttribute("servicio", servicio);
			return "servicios/editServicio";
		}else {
			servicioService.save(servicio);
			modelMap.addAttribute("message", "Servicio actualizado!");
		}
		return view;
	}

}
