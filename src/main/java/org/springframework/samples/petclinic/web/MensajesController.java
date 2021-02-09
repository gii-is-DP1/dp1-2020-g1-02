package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Mensaje;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.MensajesService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mensajes")
public class MensajesController {

	@Autowired
	private MensajesService mensajesService;
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public String listadoMensajes(ModelMap modelMap) {
		String vista = "mensajes/bandejaEntrada";
		User userlog = userService.getLoggedUser();
		Iterable<Mensaje> msjs = mensajesService.findAllByReceptor(userlog);
		modelMap.addAttribute("mensajes", msjs);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearMensaje(ModelMap modelMap) {
		String view="mensajes/newMensaje";
		modelMap.addAttribute("principal", userService.getLoggedUser());
		modelMap.addAttribute("users", userService.findAllUsernames());
		Mensaje mensaje = new Mensaje();
		mensaje.setFecha(LocalDate.now());
		mensaje.setEmisor(userService.getLoggedUser());
		modelMap.addAttribute("mensaje", mensaje);
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarMensaje(@Valid Mensaje msj, BindingResult result,ModelMap modelMap) {
		String view="redirect:/mensajes";
		if(result.hasErrors()) {
			modelMap.addAttribute("mensaje", msj);
			modelMap.addAttribute("message", "Ningún campo puede estar vacío");
			modelMap.addAttribute("users", userService.findAllUsernames());
			return "mensajes/newMensaje";
		}else {
			try {
				mensajesService.save(msj);
				modelMap.addAttribute("message", "Mensaje enviado!");
			} catch (Exception e) {
				view = "mensajes/newMensaje";
				modelMap.addAttribute("message", "El emisor y receptor no pueden estar vacíos");
			}
		}
		return view;
	}
	
	@PostMapping(path="/leido")
	public String marcarLeido(Integer id, ModelMap modelMap) {
		String view="redirect:/mensajes";
		Mensaje m=mensajesService.findById(id).get();
		mensajesService.marcarLeido(m);
		return view;
	}
	
	@GetMapping(path="/new/{mId}")
	public String responderMensaje(@PathVariable("mId") int mId, ModelMap modelMap) {
		String view="mensajes/newMensaje";
		modelMap.addAttribute("users", userService.findAllUsernames());
		Mensaje mensajeE = mensajesService.findById(mId).get();
		Mensaje mensajeR = new Mensaje();
		mensajeR.setEmisor(userService.getLoggedUser());
		List<User> l = Arrays.asList(mensajeE.getEmisor());
		mensajeR.setReceptores(l);
		mensajeR.setFecha(LocalDate.now());
		modelMap.addAttribute("mensaje", mensajeR);
		return view;
	}
	
}
