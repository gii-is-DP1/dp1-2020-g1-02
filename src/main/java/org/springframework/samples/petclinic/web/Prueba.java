package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.User;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

public class Prueba {
	
	@PostMapping(path="/actualizarCliente")
	public String actualizarCliente(@Valid Cliente cliente, BindingResult result,ModelMap modelMap) {
		String view = "";
		User principal = userService.getLoggedUser();
		if(result.hasErrors()) {
			modelMap.addAttribute("cliente", cliente);
			view = "redirect:/users/" + principal.getUsername();
		}else {
			if(principal != null && principal.getUsername().equals(cliente.getUser().getUsername())) {
				Cliente clientToUpDate = clienteService.findClienteById(cliente.getId()).get();
				
				clientToUpDate.setNombre(cliente.getNombre());
				clientToUpDate.setApellidos(cliente.getApellidos());
				clientToUpDate.setCorreo(cliente.getCorreo());
				clientToUpDate.setDireccion(cliente.getDireccion());
				clientToUpDate.setTelefono(cliente.getTelefono());
				
				
				clienteService.actualizarCliente(clientToUpDate);
				//modelMap.addAttribute("message", "Cliente actualizado!");
				view = "redirect:/users/" + principal.getUsername();
			}else {
				view = EXCEPTION_VIEW;
				modelMap.addAttribute("error", "No tienes permisos para acceder a esta página");
			}
		}
		return view;
	}

}
