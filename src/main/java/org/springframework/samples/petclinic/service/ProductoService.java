package org.springframework.samples.petclinic.service;

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
	public int ProductCount() {
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

	public Iterable<Producto> findAllById(Iterable<Integer> ids) {
		return productRepo.findAllById(ids);
	}

}
