package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.exceptions.LimitePedidoException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private OfertaService ofertaService;
	
	@GetMapping()
	public String listadoPedidos(ModelMap modelMap) {
		String vista ="administradores/listadoPedidos";
		Iterable<Pedido> pedido = pedidoService.findAll();
		modelMap.addAttribute("pedidos", pedido);
		return vista;
	}
	
	@GetMapping(path="/new/{oId}")
	public String crearPedido(@PathVariable("oId") Integer oId, ModelMap modelMap) {
		String view="administradores/editPedido";
        Pedido p = new Pedido();
        p.setOferta(ofertaService.findOfertaById(oId).get());
        p.setFechaPedido(LocalDate.now());
        modelMap.addAttribute("pedido", p);
        return view;
	}
	
	@PostMapping(path="/save")
	public String salvarPedido(@Valid Pedido pedido, BindingResult result, ModelMap modelMap) {
		String view="redirect:/pedidos";
        if(result.hasErrors()) {
            modelMap.addAttribute("pedido", pedido);
            return "administradores/editPedido";
        } else {
        	try {
        		pedidoService.crearPedido(pedido);
        		return view;
        	} catch(LimitePedidoException e) {
        		modelMap.addAttribute("pedido", pedido);
        		modelMap.addAttribute("message", "El precio total del pedido no puede superar 200 euros.");
        		return "administradores/editPedido";
        	}
        }
	}
	
}
