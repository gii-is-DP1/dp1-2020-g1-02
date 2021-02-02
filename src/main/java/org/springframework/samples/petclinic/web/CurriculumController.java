package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Curriculum;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.CurriculumService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/curriculums")
public class CurriculumController {
	
	@Autowired
	private CurriculumService curriculumService;
	
	@Autowired
	private TrabajadorService trabajadorService;
	
	@GetMapping()
	public String listadoCurriculums(ModelMap modelMap) {
		String vista ="curriculums/listadoCurriculums";
		Iterable<Curriculum> curriculums = curriculumService.findAll();
		modelMap.addAttribute("curriculum", curriculums);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearCurriculum(ModelMap modelMap) {
		String view="curriculums/newCurriculum";
		Iterable<Trabajador> trabajadores = trabajadorService.findAll();
		modelMap.addAttribute("trabajadores", trabajadores);
		modelMap.addAttribute("curriculum", new Curriculum());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarCurriculum(@Valid Curriculum curriculum, BindingResult result,ModelMap modelMap) {
		String view="redirect:/curriculums";
		if(result.hasErrors()) {
			modelMap.addAttribute("curriculums", curriculum);
			return "curriculums/newCurriculum";
		}else {
			curriculumService.save(curriculum);
			modelMap.addAttribute("message", "Curriculum guardado!");
		}
		return view;
	}
	
	
	@GetMapping(path="/delete/{curriculumId}")
	public String borrarCurriculum(@PathVariable("curriculumId") Integer curriculumId, ModelMap modelmap) {
		String view="redirect:/curriculums";
		Optional<Curriculum> curriculum = curriculumService.findCurriculumById(curriculumId);
		if(curriculum.isPresent()) {
			curriculumService.delete(curriculum.get());
			modelmap.addAttribute("message", "Curriculum borrado correctamente");
		}else {
			modelmap.addAttribute("message", "Curriculum no encontrado");
		}
		return view;
	}

}
