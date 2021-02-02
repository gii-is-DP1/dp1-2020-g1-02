package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Mensajes;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.service.MensajesService;
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
	private MensajesService mensajesAdminService;
	
	@GetMapping()
	public String listadoMensajes(ModelMap modelMap) {
		String vista = "administradores/listadoMensajes";
		Iterable<Mensajes> msjs = mensajesAdminService.findAll();
		modelMap.addAttribute("mensajes", msjs);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearMensajeAdmin(ModelMap modelMap) {
		String view="adminsitrador/newMensaje";
		modelMap.addAttribute("mensaje", new Mensajes());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarMensajeAdmin(@Valid Mensajes msj, BindingResult result,ModelMap modelMap) {
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
