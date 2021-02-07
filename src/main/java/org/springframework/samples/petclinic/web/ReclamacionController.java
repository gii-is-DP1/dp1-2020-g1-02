package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.samples.petclinic.service.ServicioService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.NoDuranteServicioException;
import org.springframework.samples.petclinic.service.exceptions.ServicioNoAceptadoException;
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
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public String listadoReclamaciones(ModelMap modelMap) {
		String vista ="reclamaciones/listadoReclamaciones";
		Iterable<Reclamacion> reclamaciones = reclamacionService.findAll();
		modelMap.addAttribute("reclamaciones", reclamaciones);
		return vista;
	}
	
	@GetMapping(value = "/{servicioId}")
	public String listadoReclamacionesPorServicioId(@PathVariable("servicioId") int servicioId,ModelMap modelMap) {
		String vista ="reclamaciones/listadoReclamacionesPorServicio";
		Iterable<Reclamacion> reclamaciones = reclamacionService.findReclamacionesByServicioId(servicioId);
		modelMap.addAttribute("reclamaciones", reclamaciones);
		return vista;
	}
	
	
	@GetMapping(path="/new/{oId}")
	public String crearReclamacion(@PathVariable("oId") Integer oId,ModelMap modelMap) {
		String view="reclamaciones/newReclamacion";
		Reclamacion r = new Reclamacion();
		r.setServicio(servicioService.findServicioById(oId).get());
		r.setFecha(LocalDate.now());
		User user = userService.getLoggedUser();
		if(user.getAuthorities().getAuthority().equalsIgnoreCase("cliente")) {
			Cliente cliente = clienteService.findClienteByUsername(user.getUsername()).get();
			modelMap.addAttribute("clientes", cliente);
			modelMap.addAttribute("reclamacion", r);
		}else {
			return "exception";
		}
		
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarReclamacion(@Valid Reclamacion reclamacion, BindingResult result,ModelMap modelMap) {
		String view="succesful";
		if(result.hasErrors()) {
			modelMap.addAttribute("reclamacion", reclamacion);
			return "reclamaciones/newReclamacion";
		}else {
			try {
				reclamacionService.comprobarExcepciones(reclamacion);
				modelMap.addAttribute("message", "Reclamacion añadida");
			} catch(ServicioNoAceptadoException e) {
				modelMap.addAttribute("reclamacion", reclamacion);
				modelMap.addAttribute("message", "No se puede poner una reclamación a un servicio que no esté aceptado");
				return "reclamaciones/newReclamacion";
			} catch(NoDuranteServicioException e) {
				modelMap.addAttribute("reclamacion", reclamacion);
				modelMap.addAttribute("message", "No se puede poner una reclamación antes de que comience el servicio ni después de que finalice");
				return "reclamaciones/newReclamacion";
			}
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
