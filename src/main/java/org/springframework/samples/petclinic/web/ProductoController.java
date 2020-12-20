package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private ProductoService productService;
	
	@GetMapping()
	public String listadoProductos(ModelMap modelMap) {
		String vista = "productos/listadoProductos";
		Iterable<Producto> productos = productService.findAll();
		modelMap.addAttribute("producto", productos);
		return vista;
	}
	
	@GetMapping(path="/edit")
	public String editaProducto(ModelMap modelMap) {
		String view="productos/editProducto";
		modelMap.addAttribute("producto", new Producto());
		return view;
	}
	
	@GetMapping(path="/new")
	public String crearProducto(ModelMap modelMap) {
		String view="productos/editProducto";
		modelMap.addAttribute("producto", new Producto());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarProducto(@Valid Producto producto, BindingResult result,ModelMap modelMap) {
		String view="redirect:/productos";
		if(result.hasErrors()) {
			modelMap.addAttribute("producto", producto);
			return "productos/edit";
		}else {
			productService.save(producto);
			modelMap.addAttribute("message", "Producto actualizado!");
			view=listadoProductos(modelMap);
		}
		return view;
	}
	
	@GetMapping(path="/{productoId}/restar")
	public String restarStock(@PathVariable("productoId") int productoId, ModelMap modelmap) {
		Producto product = productService.findProductoById(productoId).get();
		productService.restarProducto(product);
		modelmap.addAttribute("message", "Producto actualizado!");
		return "redirect:/productos";
	}

}
