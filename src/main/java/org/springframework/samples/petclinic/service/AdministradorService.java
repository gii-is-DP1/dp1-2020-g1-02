package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.repository.AdministradorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministradorService {
	
	@Autowired
	private AdministradorRepository administradorRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Transactional
	public int administradorCount() {
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

	public Optional<Administrador> findAdministradorById(int adminId) {
		return administradorRepo.findById(adminId);
	}

	public Optional<Administrador> findAdministradorByUsername(String username) {
		return administradorRepo.findAdministradorByUsername(username);
	}
	
	public void deleteById(Integer id) {
		Administrador administradorBorrar = findAdministradorById(id).get();
		delete(administradorBorrar);
	}
	
	@Transactional
	public void saveAdministrador(Administrador administrador) throws DataAccessException {
		//creating owner
		administradorRepo.save(administrador);
		//creating user
		userService.saveUser(administrador.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(administrador.getUser().getUsername(), "administrador");	
	}
	
	public void actualizarAdministrador(Administrador administrador) throws DataAccessException {
		//creating owner
		administradorRepo.save(administrador);
	}
}


