package org.springframework.samples.petclinic.web;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.AdministradorService;
import org.springframework.samples.petclinic.service.ContratoServicioService;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.samples.petclinic.service.TrabajadorService;
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
	@Autowired
	private ContratoServicioService contratoServicioService;
	@Autowired
	private ReclamacionService reclamacionService;
	@Autowired
	private TrabajadorService trabajadorService;
	
	@GetMapping()
	public String listadoAdmin(ModelMap modelMap) {
		String vista ="administradores/listadoAdmin";
		Iterable<Administrador> administrador = adminService.findAll();
		modelMap.addAttribute("administrador", administrador);
		return vista;
	}
	
	@GetMapping(path = "/reclamaciones")
	public String listadoReclamaciones(ModelMap modelMap) {
		String vista ="administradores/listadoReclamaciones";
		Iterable<Reclamacion> reclamaciones = reclamacionService.findAll();
		modelMap.addAttribute("reclamacion", reclamaciones);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearAdministrador(ModelMap modelMap) {
		String view="administradores/newAdministrador";
		Iterable<Trabajador> trabajadores = trabajadorService.findAll();
		modelMap.addAttribute("trabajadores", trabajadores);
		modelMap.addAttribute("administrador", new Administrador());
		return view;
	}
	
	@GetMapping(path="/morosos")
	public String listaMorosos(ModelMap modelMap) {
		String view="administradores/listadoMorosos";
		modelMap.addAttribute("morosos", contratoServicioService.buscaMorosos());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarAdministrador(@Valid Administrador administrador, BindingResult result,ModelMap modelMap) {
		String view="redirect:/administradores";
		if(result.hasErrors()) {
			modelMap.addAttribute("administrador", administrador);
			return "administradores/newAdministradores";
		}else {
			adminService.save(administrador);
			modelMap.addAttribute("message", "Administrador actualizado!");
		}
		return view;
	}
	
	@GetMapping(path="/delete/{adminId}")
	public String borrarAdministrador(@PathVariable("adminId") int adminId, ModelMap modelmap) {
		String view="redirect:/administradores";
		Optional<Administrador> administrador=adminService.findAdministradorById(adminId);
		if(administrador.isPresent()) {
			adminService.delete(administrador.get());
			modelmap.addAttribute("message", "Administrador borrado correctamente");
		}else {
			modelmap.addAttribute("message", "Administrador on encontrado");
		}
		return view;
	}
}
