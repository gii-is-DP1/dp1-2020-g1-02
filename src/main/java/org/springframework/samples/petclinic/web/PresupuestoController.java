package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Presupuesto;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.service.PresupuestoService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/servicios/{servicioId}/presupuestos")
public class PresupuestoController {
	
	@Autowired
	private PresupuestoService presupuestoService;
	private ServicioService servicioService;
	
//	@ModelAttribute("servicio")
//	public Servicio findServicio(@PathVariable("servicioId") int servicioId) {
//		Optional<Servicio> s= this.servicioService.findServicioById(servicioId);
//		return s.get();
//	}
	
	@GetMapping(path="/new")
	public String crearPresupuesto(@PathVariable("servicioId") Integer servicioId, ModelMap modelMap) {
		String view = "presupuestos/editPresupuesto";
		Presupuesto p= new Presupuesto();
		Optional<Servicio> servicio=servicioService.findServicioById(servicioId);
		servicio.get().addPresupuesto(p);
		modelMap.addAttribute("presupuesto", p);
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarPresupuesto(@PathVariable("servicioId")int servicioId, @Valid Presupuesto presupuesto, BindingResult result, ModelMap modelMap) {
		String view = "redirect:/servicios";
		if(result.hasErrors()) {
			modelMap.addAttribute("presupuesto", presupuesto);
			return "presupuestos/editPresupuesto";
		} else {
			Optional<Servicio> servicio=servicioService.findServicioById(servicioId);
			servicio.get().addPresupuesto(presupuesto);
			presupuestoService.save(presupuesto);
			modelMap.addAttribute("mensaje", "Presupuesto actualizado!!");
		}
		return view;
	}

}
