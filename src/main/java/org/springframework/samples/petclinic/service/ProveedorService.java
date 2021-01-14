package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorService {
	
	
	private ProveedorRepository proveedorRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
  
	public ProveedorService(ProveedorRepository proveedorRepo) {
		this.proveedorRepo = proveedorRepo;
	}
	
	@Transactional
	public int proveedorCount() {
		return (int) proveedorRepo.count();
	}
	
	@Transactional
	public Iterable<Proveedor> findAll(){
		return proveedorRepo.findAll();
	}

	public void save(Proveedor proveedor) {
		proveedorRepo.save(proveedor);
	}
	
	public void deleteProveedor(Proveedor proveedor) {
		proveedorRepo.delete(proveedor);
	}

	public void deleteById(Integer id) {
		Proveedor proveedorBorrar = findProveedorById(id).get();
		deleteProveedor(proveedorBorrar);
	}
	
	public Optional<Proveedor> findProveedorById(int id) {
		// TODO Auto-generated method stub
		return proveedorRepo.findById(id);
	}
	
	@Transactional
	public void saveProveedor(Proveedor proveedor) throws DataAccessException {
		//creating owner
		proveedorRepo.save(proveedor);
		//creating user
		userService.saveUser(proveedor.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(proveedor.getUser().getUsername(), "proveedor");	
	}
}
