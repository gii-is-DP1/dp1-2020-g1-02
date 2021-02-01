package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reclamaciones")
public class ReclamacionController {

	@Autowired
	private ReclamacionService reclamacionService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ServicioService servicioService;
	
	@GetMapping()
	public String listadoReclamaciones(ModelMap modelMap) {
		String vista ="reclamaciones/listadoReclamaciones";
		Iterable<Reclamacion> reclamaciones = reclamacionService.findAll();
		modelMap.addAttribute("reclamaciones", reclamaciones);
		return vista;
	}
	
	
	@GetMapping(path="/new")
	public String crearReclamacion(ModelMap modelMap) {
		String view="reclamaciones/newReclamacion";
		Iterable<Cliente> clientes = clienteService.findAll();
		Iterable<Servicio> servicios = servicioService.findAll();
		modelMap.addAttribute("clientes", clientes);
		modelMap.addAttribute("servicios", servicios);
		modelMap.addAttribute("reclamacion", new Reclamacion());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarReclamacion(@Valid Reclamacion reclamacion, BindingResult result,ModelMap modelMap) {
		String view="redirect:/reclamaciones";
		if(result.hasErrors()) {
			modelMap.addAttribute("reclamacion", reclamacion);
			return "reclamaciones/newReclamacion";
		}else {
			reclamacionService.save(reclamacion);
			modelMap.addAttribute("message", "Reclamación realizada!");
		}
		return view;
	}
	
	@GetMapping(path="/delete/{reclamacionId}")
	public String borrarReclamacion(@PathVariable("reclamacionId") Integer reclamacionId, ModelMap modelmap) {
		String view = "redirect:/reclamaciones";
		Optional<Reclamacion> reclamacion = reclamacionService.findReclamacionById(reclamacionId);
		reclamacionService.delete(reclamacion.get());
		modelmap.addAttribute("message", "Reclamación borrada correctamente");
		return view;
	}
}
