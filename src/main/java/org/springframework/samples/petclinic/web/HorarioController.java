package org.springframework.samples.petclinic.web;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/horarios")
public class HorarioController {
	
	@Autowired
	private HorarioService horarioService;
	
	@GetMapping()
	public String listadoHorarios(ModelMap modelMap) {
		String vista ="horarios/listadoHorarios";
		Iterable<Horario> horarios = horarioService.findAll();
		modelMap.addAttribute("horarios", horarios);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearHorario(ModelMap modelMap) {
		String view="horarios/newHorario";
		modelMap.addAttribute("horarios", new Horario());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarHorario(@Valid Horario horario, BindingResult result,ModelMap modelMap) {
		String view="redirect:/horarios";
		if(result.hasErrors()) {
			modelMap.addAttribute("horarios", horario);
			return "horarios/newHorario";
		}else {
			horarioService.save(horario);
			modelMap.addAttribute("message", "Horario actualizado!");
			view=listadoHorarios(modelMap);
		}
		return view;
	}
	
	@GetMapping(path="/delete/{horarioId}")
	public String borrarHorario(@PathVariable("horarioId") Integer horarioId, ModelMap modelmap) {
		String view = "redirect:/horarios";
		Optional<Horario> horario = horarioService.findHorarioById(horarioId);
		if(horario.isPresent()) {
			horarioService.delete(horario.get());
			modelmap.addAttribute("message", "Horario borrada correctamente");
		}else {
			modelmap.addAttribute("message", "Horario no encontrada");
			view=listadoHorarios(modelmap);
		}
		return view;
	}

}
