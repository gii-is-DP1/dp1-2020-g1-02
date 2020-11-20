package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.repository.FacturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturaService {
	
	@Autowired
	private FacturaRepository facturaRepo;
	
	@Transactional
	public int facturaCount() {
		return (int) facturaRepo.count();
	}
	
	@Transactional
	public Iterable<Factura> findAll() {
		return facturaRepo.findAll();
	}
	
	public void save(Factura factura) {
		facturaRepo.save(factura);
	}
	
	public void delete(Factura factura) {
		facturaRepo.delete(factura);
	}

	public Iterable<Factura> findAllById(Iterable<Integer> ids) {
		return facturaRepo.findAllById(ids);
	}
	
}
