package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping()
	public String listadoClientes(ModelMap modelMap) {
		String vista ="clientes/listadoClientes";
		Iterable<Cliente> clientes = clienteService.findAll();
		modelMap.addAttribute("cliente", clientes);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearCliente(ModelMap modelMap) {
		String view="clientes/newCliente";
		modelMap.addAttribute("cliente", new Cliente());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarCliente(@Valid Cliente cliente, BindingResult result,ModelMap modelMap) {
		String view="clientes/listadoClientes";
		if(result.hasErrors()) {
			modelMap.addAttribute("cliente", cliente);
			return "clientes/newCliente";
		}else {
			clienteService.save(cliente);
			modelMap.addAttribute("message", "Cliente actualizado!");
			view=listadoClientes(modelMap);
		}
		return view;
	}
	
	@GetMapping(path="/delete/{clienteId}")
	public String borrarCliente(@PathVariable("clienteId") Integer clienteId, ModelMap modelmap) {
		String view = "clientes/listadoClientes";
		Optional<Cliente> cliente = clienteService.findClienteById(clienteId);
		if(cliente.isPresent()) {
			clienteService.delete(cliente.get());
			modelmap.addAttribute("message", "Cliente borrado correctamente");
		}else {
			modelmap.addAttribute("message", "Cliente no encontrado");
			view=listadoClientes(modelmap);
		}
		return view;
	}

}
