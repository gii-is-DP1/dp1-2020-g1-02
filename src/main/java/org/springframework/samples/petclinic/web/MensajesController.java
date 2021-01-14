package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.MensajesAdmin;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.service.MensajesAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mensajes")
public class MensajesController {

	
	@Autowired
	private MensajesAdminService mensajesAdminService;
	
	@GetMapping()
	public String listadoMensajes(ModelMap modelMap) {
		String vista = "administradores/listadoMensajes";
		Iterable<MensajesAdmin> msjs = mensajesAdminService.findAll();
		modelMap.addAttribute("mensajes", msjs);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearMensajeAdmin(ModelMap modelMap) {
		String view="administrador/newMensaje";
		modelMap.addAttribute("mensaje", new MensajesAdmin());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarMensajeAdmin(@Valid MensajesAdmin msj, BindingResult result,ModelMap modelMap) {
		String view="redirect:/mensajes";
		if(result.hasErrors()) {
			modelMap.addAttribute("mensaje", msj);
			return "administradores/newMensaje";
		}else {
			mensajesAdminService.save(msj);
			modelMap.addAttribute("message", "Proveedor actualizado!");
//			view=listadoProv(modelMap);
		}
		return view;
	}
	
	
}
