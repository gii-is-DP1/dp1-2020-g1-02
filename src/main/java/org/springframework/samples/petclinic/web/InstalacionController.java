package org.springframework.samples.petclinic.web;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Instalacion;
import org.springframework.samples.petclinic.service.InstalacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instalaciones")
public class InstalacionController {
	
	@Autowired
	private InstalacionService instalacionService;
	
	@GetMapping()
	public String listadoInstalaciones(ModelMap modelMap) {
		String vista ="instalaciones/listadoInstalaciones";
		Iterable<Instalacion> instalaciones = instalacionService.findAll();
		modelMap.addAttribute("instalacion", instalaciones);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearInstalacion(ModelMap modelMap) {
		String view="instalaciones/newInstalacion";
		modelMap.addAttribute("instalacion", new Instalacion());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarInstalacion(@Valid Instalacion instalacion, BindingResult result,ModelMap modelMap) {
		String view="redirect:/instalaciones";
		if(result.hasErrors()) {
			modelMap.addAttribute("instalacion", instalacion);
			return "instalaciones/newInstalacion";
		}else {
			instalacionService.save(instalacion);
			modelMap.addAttribute("message", "Instalación actualizado!");
			view=listadoInstalaciones(modelMap);
		}
		return view;
	}
	
	@GetMapping(path="/delete/{instalacionId}")
	public String borrarInstalacion(@PathVariable("instalacionId") Integer instalacionId, ModelMap modelmap) {
		String view = "redirect:/instalaciones";
		Optional<Instalacion> instalacion = instalacionService.findInstalacionById(instalacionId);
		if(instalacion.isPresent()) {
			instalacionService.delete(instalacion.get());
			modelmap.addAttribute("message", "Instalación borrada correctamente");
		}else {
			modelmap.addAttribute("message", "Instalación no encontrada");
			view=listadoInstalaciones(modelmap);
		}
		return view;
	}

}
