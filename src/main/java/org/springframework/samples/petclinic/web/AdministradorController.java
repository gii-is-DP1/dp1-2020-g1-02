package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.service.AdministradorService;
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
		Iterable<Administrador> administrador = adminService.findAll();
		modelMap.addAttribute("administrador", administrador);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearEvento(ModelMap modelMap) {
		String view="administradores/editAdmin";
		modelMap.addAttribute("administrador", new Administrador());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarEvento(@Valid Administrador administrador, BindingResult result,ModelMap modelMap) {
		String view="administradores/listadoAdmin";
		if(result.hasErrors()) {
			modelMap.addAttribute("administrador", administrador);
			return "proveedores/editProv";
		}else {
			adminService.save(administrador);
			modelMap.addAttribute("message", "Administrador actualizado!");
			view=listadoAdmin(modelMap);
		}
		return view;
	}
	
	@GetMapping(path="/delete/{adminId}")
	public String borrarEvento(@PathVariable("adminId") int adminId, ModelMap modelmap) {
		String view="administradores/listadoAdmin";
		Optional<Administrador> administrador=adminService.findAdministradorById(adminId);
		if(administrador.isPresent()) {
			adminService.delete(administrador.get());
			modelmap.addAttribute("message", "Administrador borrado correctamente");
		}else {
			modelmap.addAttribute("message", "Administrador on encontrado");
			view=listadoAdmin(modelmap);
		}
		return view;
	}
}
