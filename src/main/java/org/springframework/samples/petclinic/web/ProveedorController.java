package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Proveedor;

import org.springframework.samples.petclinic.service.ProveedorService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping(path="proveedores/new")
	public String crearEvento(ModelMap modelMap) {
		String view="proveedores/editProv";
		modelMap.addAttribute("prov", new Proveedor());
		return view;
	}
	
	@PostMapping(path="proveedores/save")
	public String salvarEvento(@Valid Proveedor prov, BindingResult result,ModelMap modelMap) {
		String view="proveedores/listadoProv";
		if(result.hasErrors()) {
			modelMap.addAttribute("prov", prov);
			return "proveedores/editProv";
		}else {
			provService.save(prov);
			modelMap.addAttribute("message", "Proveedor actualizado!");
			view=listadoProv(modelMap);
		}
		return view;
	}
	
	@GetMapping(path="proveedores/delete/{provName}")
	public String borrarEvento(@PathVariable("provName") int provName, ModelMap modelmap) {
		String view="proveedores/listadoEventos";
		Optional<Proveedor> prov=provService.findEventById(provName);
		if(prov.isPresent()) {
			provService.delete(prov.get());
			modelmap.addAttribute("message", "Proveedor borrado correctamente");
		}else {
			modelmap.addAttribute("message", "Proveedor on encontrado");
			view=listadoProv(modelmap);
		}
		return view;
	}
}
