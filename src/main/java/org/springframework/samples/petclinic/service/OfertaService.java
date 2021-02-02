package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.OfertaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OfertaService {

	@Autowired
	private OfertaRepository ofertaRepo;
	
	@Transactional(readOnly=true)
	public int ofertasCount() {
		return (int) ofertaRepo.count();
	}
	
	@Transactional(readOnly=true)
	public Iterable<Oferta> findAll() {
		return ofertaRepo.findAll();
	}
	@Transactional
	public void save(Oferta product) {
		ofertaRepo.save(product);
	}
	@Transactional
	public void delete(Oferta product) {
		ofertaRepo.delete(product);
	}
	@Transactional(readOnly=true)
	public Iterable<Oferta> findAllById(Iterable<Integer> ids) {
		return ofertaRepo.findAllById(ids);
	}
	
	@Transactional(readOnly=true)
	public Optional<Oferta> findOfertaById(Integer id) {
		return ofertaRepo.findById(id);
	}

}
