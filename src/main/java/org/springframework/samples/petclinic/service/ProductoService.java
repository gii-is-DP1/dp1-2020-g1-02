package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {
	@Autowired
	private ProductoRepository productRepo;
	
	@Transactional
	public int productCount() {
		return (int) productRepo.count();
	}
	
	@Transactional
	public Iterable<Producto> findAll() {
		return productRepo.findAll();
	}
	
	public void save(Producto product) {
		productRepo.save(product);
	}
	
	public void delete(Producto product) {
		productRepo.delete(product);
	}
	
	public void deleteById(Integer id) {
		Producto productoBorrar = findProductoById(id).get();
		delete(productoBorrar);
	}

	public void restarProducto(Producto product) {
		Producto productoCopia = new Producto();
		productoCopia.setId(product.getId());
		productoCopia.setName(product.getName());
		productoCopia.setCantidadStock((Integer) product.getCantidadStock()-1);
		productRepo.delete(product);
		productRepo.save(productoCopia);
	}
	
	public void sumarProducto(Producto product) {
		product.setCantidadStock(product.getCantidadStock()+1);
		productRepo.save(product);
		
	}
	
	public Optional<Producto> findProductoById(Integer id) {
		return productRepo.findById(id);
	}

}
