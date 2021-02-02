package org.springframework.samples.petclinic.web;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.RegistroHoras;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.RegistroHorasService;
import org.springframework.samples.petclinic.service.TrabajadorService;
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
		Iterable<Trabajador> trabajadores = trabajadorService.findAll();
		modelMap.addAttribute("trabajadores", trabajadores);
		modelMap.addAttribute("registro_horas", new RegistroHoras());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarRegistroHoras(@Valid RegistroHoras registroHora, BindingResult result,ModelMap modelMap) {
		String view="redirect:/registroHoras";
		if(result.hasErrors()) {
			modelMap.addAttribute("registro_horas", registroHora);
			return "registroHoras/newRegistroHoras";
		}else {
			registroHorasService.saveRegistroHoras(registroHora);
			modelMap.addAttribute("message", "Registro de Horas actualizado!");
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
