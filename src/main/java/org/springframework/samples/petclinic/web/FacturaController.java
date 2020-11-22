package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.service.FacturaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/facturas")
public class FacturaController {
	
	@Autowired
	private FacturaService facturaService;
	
	@GetMapping()
	public String listadoFacturas(ModelMap modelMap) {
		String vista = "facturas/listadoFacturas";
		Iterable<Factura> facturas = facturaService.findAll();
		modelMap.addAttribute("factura", facturas);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearFactura(ModelMap modelMap) {
		String view="facturas/editFactura";
		modelMap.addAttribute("factura", new Factura());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarFactura(@Valid Factura factura, BindingResult result, ModelMap modelMap) {
		String view="facturas/listadoFacturas";
		if(result.hasErrors()) {
			modelMap.addAttribute("factura", factura);
			return "events/editEvent";
		}else {
			facturaService.save(factura);
			modelMap.addAttribute("message", "Factura actualizada!");
			view=listadoFacturas(modelMap);
		}
		return view;
	}

}