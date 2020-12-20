package org.springframework.samples.petclinic.web;

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
	
	@GetMapping(path="/new")
	public String crearFactura(ModelMap modelMap) {
		String view="facturas/editFactura";
		modelMap.addAttribute("factura", new Factura());
		return view;
	}
	
	@GetMapping(path="/filtrado/{nameProv}")
	public String filtradoFactura(@PathVariable("nameProv") String nameProv,ModelMap modelMap) {
		String view="redirect:/facturas";
		modelMap.addAttribute("facturas", facturaService.findFacturaByProveedorId(nameProv));
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
	
	@GetMapping(path="/delete/{facturaId}")
	public String borrarTrabajador(@PathVariable("facturaId") int facturaId, ModelMap modelmap) {
		String view="facturas/listadoFacturas";
		Optional<Factura> factura=facturaService.findFacturaById(facturaId);
		if(factura.isPresent()) {
			facturaService.deleteFactura(factura.get());
			modelmap.addAttribute("message", "Factura borrada correctamente");
			
		}else {
			modelmap.addAttribute("message", "Factura no encontrada");
			view=listadoFacturas(modelmap);
		}
		return view;
	}

}
