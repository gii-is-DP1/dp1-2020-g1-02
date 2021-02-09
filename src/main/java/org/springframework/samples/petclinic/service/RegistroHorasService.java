package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.samples.petclinic.model.RegistroHoras;
import org.springframework.samples.petclinic.repository.RegistroHorasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroHorasService {

	private RegistroHorasRepository registroHorasRepo;
	
	@Autowired
	public RegistroHorasService(RegistroHorasRepository registroHorasRepo) {
		super();
		this.registroHorasRepo = registroHorasRepo;
	}

	@Transactional(readOnly=true)
	public int RegistroHorasCount() {
		return (int) registroHorasRepo.count();
	}
	
	@Transactional(readOnly=true)
	public Iterable<RegistroHoras> findAll() {
		return registroHorasRepo.findAll();
	}
	
	@Transactional
	public void saveRegistroHoras(RegistroHoras registroHoras) {
		registroHorasRepo.save(registroHoras);
	}
	
	@Transactional
	public void deleteRegistroHoras(RegistroHoras registroHoras) {
		registroHorasRepo.delete(registroHoras);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		RegistroHoras registroHorasBorrar = findRegistroHorasById(id).get();
		deleteRegistroHoras(registroHorasBorrar);
	}

	@Transactional(readOnly=true)
	public Pair<Double, Iterable<RegistroHoras>> findRegistroHorasByTrabajadorId(Integer tId) {
		Iterable<RegistroHoras>it=registroHorasRepo.findRegistroHorasByTrabajadorId(tId);
		Double sum=registroHorasRepo.horasTotalesByTrabajador(tId);
		return Pair.of(sum, it);
	}
	
	@Transactional(readOnly=true)
	public Optional<RegistroHoras> findRegistroHorasById(Integer id) {
		return registroHorasRepo.findById(id);
	}

}
