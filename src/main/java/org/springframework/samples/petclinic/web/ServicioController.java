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
@RequestMapping("/servicios")
public class ServicioController {
	
	@Autowired
	private ServicioService servicioService;
	@Autowired
	private PresupuestoService presupuestoService;
	
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
	
	@PostMapping(path="/aceptar")
	public String aceptarServicio(Integer id, ModelMap modelMap) {
		String view="redirect:/servicios";
		Optional<Servicio> s= servicioService.findServicioById(id);
		servicioService.aceptar(s.get());
		return view;
	}
	
	@PostMapping(path="/rechazar")
	public String rechazarServicio(Integer id, ModelMap modelMap) {
		String view="redirect:/servicios";
		Optional<Servicio> s= servicioService.findServicioById(id);
		servicioService.rechazar(s.get());
		return view;
	}
	
	@GetMapping(value = "/{clienteId}")
	public String listadoServiciosPorClienteId(@PathVariable("clienteId") int clienteId, ModelMap modelMap) {
		String vista = "servicios/listadoServiciosPorCliente";
		Iterable<Servicio> servicios = servicioService.serviciosByCliente(clienteId);
		modelMap.addAttribute("servicios", servicios);
		return vista;
	}
	
	@GetMapping(path="/{servicioId}/presupuesto/new")
	public String crearPresupuesto(@PathVariable("servicioId") Integer servicioId, ModelMap modelMap) {
		String view = "presupuestos/editPresupuesto";
		Presupuesto p= new Presupuesto();
		p.setServicio(servicioService.findServicioById(servicioId).get());
		modelMap.addAttribute("presupuesto", p);
		return view;
	}
	
	@PostMapping(path="/presupuestos/save")
	public String salvarPresupuesto(@Valid Presupuesto presupuesto, BindingResult result, ModelMap modelMap) {
		String view = "redirect:/servicios";
		if(result.hasErrors()) {
			modelMap.addAttribute("presupuesto", presupuesto);
			return "presupuestos/editPresupuesto";
		} else {
			presupuestoService.save(presupuesto);
			modelMap.addAttribute("mensaje", "Presupuesto actualizado!!");
		}
		return view;
	}

}
