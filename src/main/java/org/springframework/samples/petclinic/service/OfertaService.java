package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.repository.OfertaRepository;
import org.springframework.transaction.annotation.Transactional;

public class OfertaService {

	@Autowired
	private OfertaRepository ofertaRepo;
	
	@Transactional
	public int OfertasCount() {
		return (int) ofertaRepo.count();
	}
	
	@Transactional
	public Iterable<Oferta> findAll() {
		return ofertaRepo.findAll();
	}
	
	public void save(Oferta product) {
		ofertaRepo.save(product);
	}
	
	public void delete(Oferta product) {
		ofertaRepo.delete(product);
	}

	public Iterable<Oferta> findAllById(Iterable<Integer> ids) {
		return ofertaRepo.findAllById(ids);
	}

}
