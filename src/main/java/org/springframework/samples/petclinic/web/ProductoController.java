package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Producto;
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
	
	@GetMapping(path="/new")
	public String crearProducto(ModelMap modelMap) {
		String view="productos/newProducto";
		modelMap.addAttribute("producto", new Producto());
		return view;
	}
	
	@PostMapping(path="/save")
	public String salvarProducto(@Valid Producto producto, BindingResult result,ModelMap modelMap) {
		String view="redirect:/productos";
		if(result.hasErrors()) {
			modelMap.addAttribute("producto", producto);
			return "productos/newProducto";
		}else {
			producto.setCantidad(0);
			productService.save(producto);
			modelMap.addAttribute("message", "Producto guardado!");
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
	
	@GetMapping(path="/{productoId}/sumar")
	public String sumarStock(@PathVariable("productoId") int productoId, ModelMap modelmap) {
		Producto product = productService.findProductoById(productoId).get();
		productService.sumarProducto(product, 1);
		modelmap.addAttribute("message", "Producto actualizado!");
		return "redirect:/productos";
	}
	
	@GetMapping(path="/delete/{productoId}")
	public String borrarProducto(@PathVariable("productoId") int productoId, ModelMap modelmap) {
		Producto product = productService.findProductoById(productoId).get();
		productService.delete(product);
		modelmap.addAttribute("message", "Producto borrado!");
		return "redirect:/productos";
	}
}
