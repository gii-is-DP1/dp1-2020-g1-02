package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trabajadores")
public class TrabajadorController {

	@Autowired
	private TrabajadorService trabajadorService;
	
	@GetMapping()
	public String listadoTrabajadores(ModelMap modelMap) {
		String vista ="trabajadores/listadoTrabajadores";
		Iterable<Trabajador> trabajadores = trabajadorService.findAll();
		modelMap.addAttribute("trabajadores", trabajadores);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearTrabajador(ModelMap modelMap) {
		String view="trabajadores/editTrabajadores";
		modelMap.addAttribute("trabajador", new Trabajador());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarTrabajador(@Valid Trabajador trabajador, BindingResult result,ModelMap modelMap) {
		String view="redirect:/trabajadores";
		if(result.hasErrors()) {
			modelMap.addAttribute("trabajador", trabajador);
			return "trabajadores/editTrabajadores";
		}else {
			trabajadorService.save(trabajador);
			modelMap.addAttribute("message", "Trabajador actualizado!");
			view=listadoTrabajadores(modelMap);
		}
		return view;
	}
	
	@GetMapping(value = "/{trabajadorId}/edit")
	public String iniciarFormularioActualizacionTrabajador(@PathVariable("trabajadorId") int trabajadorId, ModelMap modelMap) {
		Trabajador trabajador = this.trabajadorService.findTrabajadorById(trabajadorId).get();
		modelMap.addAttribute(trabajador);
		return "trabajadores/editTrabajadores";
	}
	
	@GetMapping(path="/delete/{trabajadorId}")
	public String borrarTrabajador(@PathVariable("trabajadorId") int trabajadorId, ModelMap modelmap) {
		String view="redirect:/trabajadores";
		Optional<Trabajador> trabajador=trabajadorService.findTrabajadorById(trabajadorId);
		if(trabajador.isPresent()) {
			
			trabajadorService.delete(trabajador.get());
			modelmap.addAttribute("message", "Trabajador borrado correctamente");
		}else {
			modelmap.addAttribute("message", "Trabajador on encontrado");
			view=listadoTrabajadores(modelmap);
		}
		return view;
	}
}
