package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.service.FacturaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		modelMap.addAttribute("facturas", facturas);
		return vista;
	}
	
	@GetMapping(path="/{facturaId}")
	public String verFactura(@PathVariable("facturaId") int facturaId, ModelMap modelMap) {
		String vista = "facturas/verFactura";

		Factura factura = facturaService.findFacturaById(facturaId).get();
		modelMap.addAttribute("factura", factura);
		return vista;
	}
	
	
	
	@GetMapping(path="/filtrado")
	public String filtradoFactura(String nameProv,ModelMap modelMap) {
		String view="facturas/listadoFacturas";
		modelMap.addAttribute("filtrado", nameProv);
		modelMap.addAttribute("facturas", facturaService.findFacturaByProveedorName(nameProv));
		return view;
	}
	
	

}
