package org.springframework.samples.petclinic.web;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.RegistroHoras;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.RegistroHorasService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registroHoras")
public class RegistroHorasController {
	
	@Autowired
	private RegistroHorasService registroHorasService;
	
	@Autowired
	private TrabajadorService trabajadorService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public String listadoRegistroHoras(ModelMap modelMap) {
		String vista ="registroHoras/listadoRegistroHoras";
		Iterable<RegistroHoras> registroHoras = registroHorasService.findAll();
		modelMap.addAttribute("registro_horas", registroHoras);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearRegistroHoras(ModelMap modelMap) {
		String view="registroHoras/newRegistroHoras";
		User user = userService.getLoggedUser();
		if(user.getAuthorities().getAuthority().equalsIgnoreCase("trabajador") ) {
			Trabajador trabajador = trabajadorService.findTrabajadorByUsername(user.getUsername()).get();
			modelMap.addAttribute("trabajador", trabajador);
			modelMap.addAttribute("registro_horas", new RegistroHoras());
		}else {
			return "exception";
		}
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarRegistroHoras(@Valid RegistroHoras registroHora, BindingResult result,ModelMap modelMap) {
		String view="succesful";
		if(result.hasErrors()) {
			modelMap.addAttribute("registro_horas", registroHora);
			return "registroHoras/newRegistroHoras";
		}else {
			if(!trabajadorService.findTrabajadorById(registroHora.getTrabajador().getId()).isPresent()) {
			modelMap.addAttribute("registroHora", registroHora);
			modelMap.addAttribute("error", "No existe el trabajador que ha seleccionado.");
			return "registroHoras/newRegistroHoras";
		} else {
			registroHorasService.saveRegistroHoras(registroHora);
			modelMap.addAttribute("message", "Registro Hora añadida!");
			}
		
		}
		return view;
	}
	
	@GetMapping(path="/delete/{registroHorasId}")
	public String borrarRegistroHoras(@PathVariable("registroHorasId") int registroHorasId, ModelMap modelmap) {
		String view="redirect:/registroHoras";
		Optional<RegistroHoras> registroHoras = registroHorasService.findRegistroHorasById(registroHorasId);
		if(registroHoras.isPresent()) {
			registroHorasService.deleteRegistroHoras(registroHoras.get());
			modelmap.addAttribute("message", "Registro de Horas borrado correctamente");
		}else {
			modelmap.addAttribute("message", "Registro de Horas no encontrado");
		}
		return view;
	}

}
