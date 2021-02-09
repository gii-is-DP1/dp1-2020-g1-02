package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.repository.TrabajadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrabajadorService {

	private TrabajadorRepository trabajadorRepo;
	
	private UserService userService;
	
	private AuthoritiesService authoritiesService;
	
	@Autowired
	public TrabajadorService(TrabajadorRepository trabajadorRepo, UserService userService,
			AuthoritiesService authoritiesService) {
		super();
		this.trabajadorRepo = trabajadorRepo;
		this.userService = userService;
		this.authoritiesService = authoritiesService;
	}

	@Transactional
	public int eventCount() {
		return (int) trabajadorRepo.count();
	}
	
	@Transactional
	public Iterable<Trabajador> findAll(){
		return trabajadorRepo.findAll();
	}

	@Transactional
	public void save(Trabajador trabajador) {
		trabajadorRepo.save(trabajador);
	}
	
	@Transactional
	public void delete(Trabajador trabajador) {
		trabajadorRepo.delete(trabajador);
	}

	@Transactional(readOnly=true)
	public Optional<Trabajador> findTrabajadorById(Integer trabajadorId) {
		return trabajadorRepo.findById(trabajadorId);
	}
	
	@Transactional(readOnly=true)
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
	
	@Transactional
	public void actualizarTrabajador(Trabajador trabajador) throws DataAccessException {
		trabajadorRepo.save(trabajador);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Trabajador> findTrabajadoresByServicio(Integer servicio) {
		return trabajadorRepo.trabajadoresByServicio(servicio);
	}
	
	@Transactional(readOnly=true)
	public List<String> getNombres(){
		return trabajadorRepo.findAllNames();
	}
	
	public Iterable<Trabajador> findTrabajadoresNotInServicio(Servicio servicio){
		return trabajadorRepo.trabajadoresByNotServicio(servicio.getTrabajadores());
	}
}
