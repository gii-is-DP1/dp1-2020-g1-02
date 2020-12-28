package org.springframework.samples.petclinic.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.repository.TrabajadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrabajadorService {
	@Autowired
	private TrabajadorRepository trabajadorRepo;
	
	@Transactional
	public int eventCount() {
		return (int) trabajadorRepo.count();
	}
	
	@Transactional
	public Iterable<Trabajador> findAll(){
		return trabajadorRepo.findAll();
	}

	public void save(Trabajador trabajador) {
		trabajadorRepo.save(trabajador);
	}
	
	public void delete(Trabajador trabajador) {
		trabajadorRepo.delete(trabajador);
	}

	public Optional<Trabajador> findTrabajadorById(int trabajadorId) {
		// TODO Auto-generated method stub
		return trabajadorRepo.findById(trabajadorId);
	}

	
}
