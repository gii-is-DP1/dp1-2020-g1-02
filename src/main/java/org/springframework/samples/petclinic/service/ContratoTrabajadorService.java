package org.springframework.samples.petclinic.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.ContratoTrabajador;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.repository.ContratoTrabajadorRepository;
import org.springframework.samples.petclinic.repository.TrabajadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContratoTrabajadorService {
	@Autowired
	private ContratoTrabajadorRepository contratoTrabajadorRepo;
	
	@Transactional
	public int contratoTrabajadorCount() {
		return (int) contratoTrabajadorRepo.count();
	}
	
	@Transactional
	public Iterable<ContratoTrabajador> findAll(){
		return contratoTrabajadorRepo.findAll();
	}
	
	@Transactional
	public void save(ContratoTrabajador contratoTrabajador) {
		contratoTrabajadorRepo.save(contratoTrabajador);
	}
	
	@Transactional
	public void delete(ContratoTrabajador contratoTrabajador) {
		contratoTrabajadorRepo.delete(contratoTrabajador);
		
	}

	public Optional<ContratoTrabajador> findContratoTrabajadorById(int contratoId) {
		// TODO Auto-generated method stub
		return contratoTrabajadorRepo.findById(contratoId);
	}

	
}
