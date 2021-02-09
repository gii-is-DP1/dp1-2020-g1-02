package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.ContratoServicio;
import org.springframework.samples.petclinic.model.ContratoTrabajador;
import org.springframework.samples.petclinic.service.ContratoServicioService;
import org.springframework.samples.petclinic.service.ContratoTrabajadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contratos")
public class ContratoController {
	
	@Autowired
	private ContratoServicioService contratoServicioService;
	@Autowired
	private ContratoTrabajadorService contratoTrabajadorService;
	
	@GetMapping()
	public String listadoContratosServicios(ModelMap modelMap) {
		String view="contratos/listadoContratos";
		Iterable<ContratoServicio> contratosS = contratoServicioService.findAll();
		Iterable<ContratoTrabajador> contratosT=contratoTrabajadorService.findAll();
		Iterable<ContratoServicio> contratosServicioQueCaducanEsteMes = contratoServicioService.contratosQueCaducanEsteMes();
		Iterable<ContratoTrabajador> contratosTrabajadorQueCaducanEsteMes=contratoTrabajadorService.contratosTrabajdorQueCaducanEsteMes();
		modelMap.addAttribute("avisoServicio", contratosServicioQueCaducanEsteMes);
		modelMap.addAttribute("avisoTrabajador", contratosTrabajadorQueCaducanEsteMes);
		modelMap.addAttribute("contratosServicios", contratosS);
		modelMap.addAttribute("contratosTrabajador", contratosT);
		return view;
	}
}
