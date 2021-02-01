package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.Valoracion;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.samples.petclinic.service.ValoracionService;
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
	
	@GetMapping()
	public String listadoValoraciones(ModelMap modelMap) {
		String vista ="valoraciones/listadoValoraciones";
		Iterable<Valoracion> valoraciones = valoracionService.findAll();
		modelMap.addAttribute("valoraciones", valoraciones);
		return vista;
	}
	
	
	@GetMapping(path="/new")
	public String crearValoracion(ModelMap modelMap) {
		String view="valoraciones/newValoracion";
		Iterable<Cliente> clientes = clienteService.findAll();
		Iterable<Servicio> servicios = servicioService.findAll();
		modelMap.addAttribute("clientes", clientes);
		modelMap.addAttribute("servicios", servicios);
		modelMap.addAttribute("valoracion", new Valoracion());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarValoracion(@Valid Valoracion valoracion, BindingResult result,ModelMap modelMap) {
		String view="redirect:/valoraciones";
		if(result.hasErrors()) {
			modelMap.addAttribute("valoracion", valoracion);
			return "valoraciones/newValoracion";
		}else {
			valoracionService.save(valoracion);
			modelMap.addAttribute("message", "Valoracion realizada!");
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
