package org.springframework.samples.petclinic.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.RegistroHoras;
import org.springframework.samples.petclinic.repository.RegistroHorasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroHorasService {
	
	@Autowired
	private RegistroHorasRepository registroHorasRepo;
	
	@Transactional
	public int RegistroHorasCount() {
		return (int) registroHorasRepo.count();
	}
	
	@Transactional
	public Iterable<RegistroHoras> findAll() {
		return registroHorasRepo.findAll();
	}
	
	public void saveRegistroHoras(RegistroHoras registroHoras) {
		registroHorasRepo.save(registroHoras);
	}
	
	public void deleteRegistroHoras(RegistroHoras registroHoras) {
		registroHorasRepo.delete(registroHoras);
	}
	
	public void deleteById(Integer id) {
		RegistroHoras registroHorasBorrar = findRegistroHorasById(id).get();
		deleteRegistroHoras(registroHorasBorrar);
	}

	public Iterable<RegistroHoras> findRegistroHorasByTrabajadorId(String name) {
		return null;
		//return registroHorasRepo.findRegistroHorasByTrabajadorId(name);
	}
	
	
	public Optional<RegistroHoras> findRegistroHorasById(Integer id) {
		return registroHorasRepo.findById(id);
	}


}
