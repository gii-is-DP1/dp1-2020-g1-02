package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.ContratoServicio;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ContratoServicioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contratosServicios")
public class ContratoServicioController {
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ContratoServicioService contratoServicioService;
	
	@GetMapping()
	public String listadoContratosServicios(ModelMap modelMap) {
		String vista ="contratosServicios/listadoContratosServicios";
		Iterable<ContratoServicio> contratos = contratoServicioService.findAll();
		Iterable<ContratoServicio> contratosQueCaducanEsteMes = contratoServicioService.contratosQueCaducanEsteMes();
		modelMap.addAttribute("aviso", contratosQueCaducanEsteMes);
		modelMap.addAttribute("contratosServicios", contratos);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearContratoServicio(ModelMap modelMap) {
		String view="contratosServicios/editContratoServicio";
		modelMap.addAttribute("contratoServicio", new ContratoServicio());
		return view;
	}
	
	@GetMapping(value = "/misContratos")
	public String listadoServiciosPorClienteUsername(ModelMap modelMap) {
		String vista ="contratosServicios/listadoContratosServicios";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Cliente client = clienteService.findClienteByUsername(auth.getName()).get();
		Iterable<ContratoServicio> contratos = contratoServicioService.contratosByIdCliente(client.getId());		
		modelMap.addAttribute("contratosServicios", contratos);
		return vista;
	}
	
	
	@PostMapping(path="/save")
	public String salvarContratoServicio(@Valid ContratoServicio contratoServicio, BindingResult result, ModelMap modelMap) {
		String view="redirect:/contratosServicios";
		if(result.hasErrors()) {
			modelMap.addAttribute("contratoServicio", contratoServicio);
			return "contratosServicios/editContratoServicio";
		}else {
			contratoServicioService.save(contratoServicio);
			modelMap.addAttribute("mensaje", "Contrato del servicio actualizado!");
//			view=listadoContratosServicios(modelMap);
		}
		return view;
	}

}
