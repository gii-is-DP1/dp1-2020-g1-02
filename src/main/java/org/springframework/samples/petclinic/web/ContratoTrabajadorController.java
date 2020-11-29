package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.ContratoTrabajador;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.ContratoTrabajadorService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contratosTrabajadores")
public class ContratoTrabajadorController {

	@Autowired
	private ContratoTrabajadorService contratoTrabajadorService;
	
	@GetMapping()
	public String listadoContratosTrabajadores(ModelMap modelMap) {
		String vista ="contratosTrabajadores/listadoContratosTrabajadores";
		Iterable<ContratoTrabajador> contratos = contratoTrabajadorService.findAll();
		modelMap.addAttribute("contratosTrabajadores", contratos);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearContratoTrabajador(ModelMap modelMap) {
		String view="contratosTrabajadores/editContratoTrabajador";
		modelMap.addAttribute("contratoTrabajador", new ContratoTrabajador());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarContratoTrabajador(@Valid ContratoTrabajador contratoTrabajador, BindingResult result,ModelMap modelMap) {
		String view="redirect:/contratosTrabajadores";
		if(result.hasErrors()) {
			modelMap.addAttribute("contratoTrabajador", contratoTrabajador);
			return "contratosTrabajadores/editContratoTrabajador";
		}else {
			contratoTrabajadorService.save(contratoTrabajador);
			modelMap.addAttribute("message", "Contrato actualizado!");
			view=listadoContratosTrabajadores(modelMap);
		}
		return view;
	}
	
	@GetMapping(path="/delete/{contratoTrabajadorId}")
	public String borrarContratoTrabajador(@PathVariable("contratoTrabajadorId") int contratoTrabajadorId, ModelMap modelmap) {
		String view="redirect:/contratosTrabajadores";
		Optional<ContratoTrabajador> contratoTrabajador=contratoTrabajadorService.findContratoTrabajadorById(contratoTrabajadorId);
		if(contratoTrabajador.isPresent()) {
			contratoTrabajadorService.delete(contratoTrabajador.get());
			modelmap.addAttribute("message", "Contrato trabajador borrado correctamente");
		}else {
			modelmap.addAttribute("message", "Contrato trabajador no encontrado");
			view=listadoContratosTrabajadores(modelmap);
		}
		return view;
	}
}
