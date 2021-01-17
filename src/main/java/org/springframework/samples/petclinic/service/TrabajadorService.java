package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.repository.TrabajadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrabajadorService {
	@Autowired
	private TrabajadorRepository trabajadorRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
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
	
	@Transactional
	public Optional<Trabajador> findTrabajadorByUsername(String trabajadorUsername) {
		return trabajadorRepo.findTrabajadorByUsername(trabajadorUsername);
	}

	@Transactional
	public void saveTrabajador(Trabajador trabajador) throws DataAccessException {
		//creating trabajador
		trabajadorRepo.save(trabajador);
		//creating user
		userService.saveUser(trabajador.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(trabajador.getUser().getUsername(), "trabajador");	
	}
}
