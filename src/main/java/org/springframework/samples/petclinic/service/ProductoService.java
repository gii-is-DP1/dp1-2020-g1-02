package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {
	
	private ProductoRepository productRepo;
	
	@Autowired
	public ProductoService(ProductoRepository productoRepo) {
		this.productRepo = productoRepo;
	}
	
	@Transactional(readOnly=true)
	public int productCount() {
		return (int) productRepo.count();
	}
	
	@Transactional(readOnly=true)
	public Iterable<Producto> findAll() {
		return productRepo.findAll();
	}
	
	@Transactional
	public void save(Producto product) {
		productRepo.save(product);
	}
	
	@Transactional
	public void delete(Producto product) {
		productRepo.delete(product);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Producto productoBorrar = findProductoById(id).get();
		delete(productoBorrar);
	}

	@Transactional
	public void restarProducto(Producto product) {
		if(product.getCantidad()>0) productRepo.modificarPruducto(product.getId(), product.getCantidad()-1);
	}
	
	@Transactional
	public void sumarProducto(Producto product, Integer cantidad) {
		Integer cantidadActual = product.getCantidad() + cantidad;
		productRepo.modificarPruducto(product.getId(), cantidadActual);
	}

	@Transactional(readOnly=true)
	public Optional<Producto> findProductoById(Integer id) {
		return productRepo.findById(id);
	}
	
	@Transactional(readOnly=true)
	public Optional<Producto> findByName(String name) {
		return productRepo.findByName(name);
	}

	@Transactional
	public List<String> getNombres(){
		return productRepo.findAllNames();
		
	}
}
