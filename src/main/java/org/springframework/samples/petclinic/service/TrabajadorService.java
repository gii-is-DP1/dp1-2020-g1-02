package org.springframework.samples.petclinic.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.repository.TrabajadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrabajadorService {
	@Autowired
	private TrabajadorRepository eventRepo;
	
	@Transactional
	public int eventCount() {
		return (int) eventRepo.count();
	}
	
	@Transactional
	public Iterable<Trabajador> findAll(){
		return eventRepo.findAll();
	}

	public void save(Trabajador event) {
		eventRepo.save(event);
	}
	
	public void delete(Trabajador event) {
		eventRepo.delete(event);
		
	}

	public Optional<Trabajador> findEventById(int eventId) {
		// TODO Auto-generated method stub
		return eventRepo.findById(eventId);
	}

	
}
