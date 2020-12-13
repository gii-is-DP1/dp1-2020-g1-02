package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Presupuesto;
import org.springframework.samples.petclinic.service.PresupuestoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/presupuestos")
public class PresupuestoController {
	
	@Autowired
	private PresupuestoService presupuestoService;
	
	@GetMapping()
	public String listadoPresupuesto(ModelMap modelMap) {
		String vista = "presupuestos/listadoPresupuesto";
		Iterable<Presupuesto> presupuesto = presupuestoService.findAll();
		modelMap.addAttribute("presupuesto", presupuesto);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearPresupuesto(ModelMap modelMap) {
		String view = "presupuestos/editPresupuesto";
		modelMap.addAttribute("presupuesto", new Presupuesto());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarPresupuesto(@Valid Presupuesto presupuesto, BindingResult result, ModelMap modelMap) {
		String view = "presupuestos/listadoPresupuesto";
		if(result.hasErrors()) {
			modelMap.addAttribute("presupuesto", presupuesto);
			return "presupuestos/editPresupuesto";
		} else {
			presupuestoService.save(presupuesto);
			modelMap.addAttribute("mensaje", "Presupuesto actualizado!!");
			view = listadoPresupuesto(modelMap);
		}
		return view;
	}

}
