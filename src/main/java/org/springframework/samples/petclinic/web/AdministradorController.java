package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.AdministradorService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administradores")
public class AdministradorController {
	@Autowired
	private AdministradorService adminService;
	
	@GetMapping()
	public String listadoAdmin(ModelMap modelMap) {
		String vista ="administradores/listadoAdmin";
		Iterable<Administrador> admins = adminService.findAll();
		modelMap.addAttribute("admin", admins);
		return vista;
	}
	
	@GetMapping(path="administradores/new")
	public String crearEvento(ModelMap modelMap) {
		String view="administradores/editAdmin";
		modelMap.addAttribute("admin", new Administrador());
		return view;
	}
	
	@PostMapping(path="administradores/save")
	public String salvarEvento(@Valid Administrador admin, BindingResult result,ModelMap modelMap) {
		String view="administradores/listadoAdmin";
		if(result.hasErrors()) {
			modelMap.addAttribute("admin", admin);
			return "proveedores/editProv";
		}else {
			adminService.save(admin);
			modelMap.addAttribute("message", "Administrador actualizado!");
			view=listadoAdmin(modelMap);
		}
		return view;
	}
	
	@GetMapping(path="proveedores/delete/{provName}")
	public String borrarEvento(@PathVariable("provName") int provName, ModelMap modelmap) {
		String view="proveedores/listadoEventos";
		Optional<Administrador> admin=adminService.findEventById(provName);
		if(admin.isPresent()) {
			adminService.delete(admin.get());
			modelmap.addAttribute("message", "Administrador borrado correctamente");
		}else {
			modelmap.addAttribute("message", "Administrador on encontrado");
			view=listadoAdmin(modelmap);
		}
		return view;
	}
}
