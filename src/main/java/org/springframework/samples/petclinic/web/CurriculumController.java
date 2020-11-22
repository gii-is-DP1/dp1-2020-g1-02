package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Curriculum;
import org.springframework.samples.petclinic.service.CurriculumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/curriculums")
public class CurriculumController {
	
	@Autowired
	private CurriculumService curriculumService;
	
	@GetMapping()
	public String listadoCurriculums(ModelMap modelMap) {
		String vista ="curriculums/listadoCurriculums";
		Iterable<Curriculum> curriculums = curriculumService.findAll();
		modelMap.addAttribute("curriculum", curriculums);
		return vista;
	}
	
	
//	@GetMapping(path="/delete/{nombreCurriculum}")
//	public String borrarCurriculum(@PathVariable("nombreCurriculum") String nombre, ModelMap modelmap) {
//		String view="curriculums/listadoCurriculums";
//		Optional<Curriculum> curriculum = CurriculumService.findCurriculumByNombre(nombre);
//		if(curriculum.isPresent()) {
//			CurriculumService.delete(curriculum.get());
//			modelmap.addAttribute("message", "Curriculum borrado correctamente");
//		}else {
//			modelmap.addAttribute("message", "Curriculum no encontrado");
//			view=listadoCurriculums(modelmap);
//		}
//		return view;
//	}

}
