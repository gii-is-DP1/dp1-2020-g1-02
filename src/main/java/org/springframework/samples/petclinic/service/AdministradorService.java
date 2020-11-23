package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.repository.AdministradorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministradorService {
	
	@Autowired
	private AdministradorRepository administradorRepo;
	
	@Transactional
	public int proveedorCount() {
		return (int) administradorRepo.count();
	}
	
	@Transactional
	public Iterable<Administrador> findAll(){
		return administradorRepo.findAll();
	}

	public void save(Administrador administrador) {
		administradorRepo.save(administrador);
	}
	
	public void delete(Administrador administrador) {
		administradorRepo.delete(administrador);
	}

	public Optional<Administrador> findEventById(int administradorName) {
		// TODO Auto-generated method stub
		return administradorRepo.findById(administradorName);
	}
}

