package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacto")
public class ContactoController{

//	@Autowired
//	private OfertaService ofertaService;
	
	@GetMapping()
	public String creaMensaje(ModelMap modelMap) {
		String vista ="proveedores/editOferta";
		modelMap.addAttribute("oferta", new Oferta());
		return vista;
	}
	
//	@GetMapping(path="proveedores/new")
//	public String crearOferta(ModelMap modelMap) {
//		String view="proveedores/editOferta";
//		modelMap.addAttribute("oferta", new Oferta());
//		return view;
//	}
//	
//	@PostMapping(path="proveedores/save")
//	public String salvarOfertas(@Valid Oferta oferta, BindingResult result,ModelMap modelMap) {
//		String view="proveedores/listadoProv";
//		if(result.hasErrors()) {
//			modelMap.addAttribute("oferta", oferta);
//			return "proveedores/editOferta";
//		}else {
//			ofertaService.save(oferta);
//			modelMap.addAttribute("message", "Proveedor actualizado!");
//			view=creaMensaje(modelMap);
//		}
//		return view;
//	}
}

