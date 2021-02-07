package org.springframework.samples.petclinic.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.ProveedorService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

	@Autowired
	private ProveedorService provService;

	
	@GetMapping()
	public String listadoProv(ModelMap modelMap) {
		String vista ="proveedores/listadoProv";
		Iterable<Proveedor> provs = provService.findAll();
		modelMap.addAttribute("prov", provs);
		return vista;
	}
	
	@GetMapping(value = "/{proveedorId}/edit")
	public String editarProveedor(@PathVariable("proveedorId") int proveedorId, ModelMap modelMap) {
		Proveedor proveedor = this.provService.findProveedorById(proveedorId).get();
		modelMap.addAttribute(proveedor);
		return "proveedores/editProveedores";
	}
	@GetMapping(path="/delete/{provId}")
	public String borrarTrabajador(@PathVariable("provId") int provId, ModelMap modelmap) {
		String view="redirect:/proveedores";
		Optional<Proveedor> proveedor=provService.findProveedorById(provId);
		if(proveedor.isPresent()) {
			
			provService.deleteProveedor(proveedor.get());
			modelmap.addAttribute("message", "Proveedor borrado correctamente");
		}
		return view;
	}
	
}
