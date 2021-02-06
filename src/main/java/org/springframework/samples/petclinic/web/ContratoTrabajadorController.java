package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.ContratoTrabajador;
import org.springframework.samples.petclinic.service.ContratoTrabajadorService;
import org.springframework.samples.petclinic.service.TrabajadorService;
import org.springframework.samples.petclinic.service.exceptions.SolapamientoFechasException;
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
	@Autowired
	private TrabajadorService trabajadorService;
	
	@GetMapping()
	public String listadoContratosTrabajadores(ModelMap modelMap) {
		String vista ="contratosTrabajadores/listadoContratosTrabajadores";
		Iterable<ContratoTrabajador> contratos = contratoTrabajadorService.findAll();
		modelMap.addAttribute("contratosTrabajadores", contratos);
		return vista;
	}
	
	@GetMapping(path="/{tId}/new")
	public String crearContratoTrabajador(@PathVariable("tId") int tId, ModelMap modelMap) {
		String view="contratosTrabajadores/editContratoTrabajador";
		ContratoTrabajador c= new ContratoTrabajador();
		c.setTrabajador(trabajadorService.findTrabajadorById(tId).get());
		modelMap.addAttribute("contratoTrabajador", c);
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarContratoTrabajador(@Valid ContratoTrabajador contratoTrabajador, BindingResult result,ModelMap modelMap) {
		String view="redirect:/contratosTrabajadores/" + contratoTrabajador.getTrabajador().getId();
		if(result.hasErrors()) {
			modelMap.addAttribute("contratoTrabajador", contratoTrabajador);
			return "contratosTrabajadores/editContratoTrabajador";
		}else {
			try {
				contratoTrabajadorService.crearContrato(contratoTrabajador);
				modelMap.addAttribute("message", "Contrato actualizado!");
				return view;
			} catch(SolapamientoFechasException e) {
				modelMap.addAttribute("message", "El trabajador ya tiene un contrato en esas fechas");
				return "contratosTrabajadores/editContratoTrabajador";
			}
		}
	}
	
	@GetMapping(path="/delete/{contratoTrabajadorId}")
	public String borrarContratoTrabajador(@PathVariable("contratoTrabajadorId") int contratoTrabajadorId, ModelMap modelmap) {
		String view="redirect:/contratos";
		Optional<ContratoTrabajador> contratoTrabajador=contratoTrabajadorService.findContratoTrabajadorById(contratoTrabajadorId);
		if(contratoTrabajador.isPresent()) {
			contratoTrabajadorService.delete(contratoTrabajador.get());
			modelmap.addAttribute("message", "Contrato trabajador borrado correctamente");
		}else {
			modelmap.addAttribute("message", "Contrato trabajador no encontrado");
		}
		return view;
	}
	
	@GetMapping(path="/{trabajadorId}")
	public String contratosByTrabajador(@PathVariable("trabajadorId") Integer tId, ModelMap modelMap) {
		String view="contratosTrabajadores/contratosByTrabajador";
		Iterable<ContratoTrabajador> contratos=contratoTrabajadorService.findContratoTrabajadorByTrabajador(tId);
		modelMap.addAttribute("contratos", contratos);
		return view;
	}
}
