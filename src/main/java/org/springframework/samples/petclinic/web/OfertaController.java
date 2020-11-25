package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ofertas")
public class OfertaController {

	@Autowired
	private OfertaService ofertaService;
	@Autowired
	private ProductoService productoService;
	
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
		modelMap.addAttribute("productos", productos);
		modelMap.addAttribute("oferta", new Oferta());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarOferta(@Valid Oferta oferta, BindingResult result, ModelMap modelMap) {
		String view="ofertas/listadoOfertas";
		if(result.hasErrors()) {
			modelMap.addAttribute("oferta", oferta);
			return "ofertas/editOferta";
		}else {
			ofertaService.save(oferta);
			modelMap.addAttribute("message", "Oferta añadida!");
			view=listadoOfertas(modelMap);
		}
		return view;
	}
	
//	@GetMapping(path="/delete/{ofertaId}")
//	public String borrarTrabajador(@PathVariable("trabajadorId") int trabajadorId, ModelMap modelmap) {
//		String view="trabajadores/listadoTrabajadores";
//		Optional<Trabajador> trabajador=trabajadorService.findTrabajadorById(trabajadorId);
//		if(trabajador.isPresent()) {
//			trabajadorService.delete(trabajador.get());
//			modelmap.addAttribute("message", "Trabajador borrado correctamente");
//		}else {
//			modelmap.addAttribute("message", "Trabajador on encontrado");
//			view=listadoTrabajadores(modelmap);
//		}
//		return view;
//	}
	
}

