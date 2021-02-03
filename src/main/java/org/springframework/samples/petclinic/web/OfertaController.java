package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ofertas")
public class OfertaController {

	@Autowired
	private OfertaService ofertaService;
	@Autowired
	private ProductoService productoService;
	@Autowired
	private ProveedorService proveedorService;
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public String listadoOfertas(ModelMap modelMap) {
		String vista ="ofertas/listadoOfertas";
		Iterable<Oferta> ofertas = ofertaService.findAll();
		modelMap.addAttribute("ofertas", ofertas);
		return vista;
	}
	
	@GetMapping(path="/new")
	public String crearOferta(ModelMap modelMap) {
		String view="ofertas/editOferta";
		Iterable<Producto> productos = productoService.findAll();
		User user =  userService.getLoggedUser();
		if(user.getAuthorities().getAuthority().equalsIgnoreCase("proveedor") ) {
			Proveedor prov = proveedorService.findProveedorByUsername(user.getUsername()).get();
			modelMap.addAttribute("proveedor", prov);
			modelMap.addAttribute("productos", productos);
			modelMap.addAttribute("oferta", new Oferta());
		}else {
			return "exception";
		}
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarOferta(@Valid Oferta oferta, BindingResult result, ModelMap modelMap) {
		String view="succesful";
		Optional<Producto> producto = productoService.findByName(oferta.getName());
		oferta.setProducto(producto.get());
		if(result.hasErrors()) {
			modelMap.addAttribute("oferta", oferta);
			return "ofertas/editOferta";
		}else {
			if(!proveedorService.findProveedorById(oferta.getProveedor().getId()).isPresent()) {
				modelMap.addAttribute("oferta", oferta);
				modelMap.addAttribute("error", "No existe el proveedor que ha seleccionado.");
				return "ofertas/editOferta";
			}else {
			ofertaService.save(oferta);
			modelMap.addAttribute("message", "Oferta añadida!");
			}
		}
		return view;
	}
	
	@GetMapping(path="/delete/{ofertaId}")
	public String borrarOferta(@PathVariable("ofertaId") int ofertaId, ModelMap modelmap) {
		Oferta oferta = ofertaService.findOfertaById(ofertaId).get();
		ofertaService.delete(oferta);
		modelmap.addAttribute("message", "oferta borrada!");
		return "redirect:/ofertas";
	}
	
}

