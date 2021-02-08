package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorService {
	
	@Autowired
	private ProveedorRepository proveedorRepo;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private FacturaService facturaService;
	
	@Transactional
	public int proveedorCount() {
		return (int) proveedorRepo.count();
	}
	
	@Transactional
	public Iterable<Proveedor> findAll(){
		return proveedorRepo.findAll();
	}

	@Transactional
	public void deleteProveedor(Proveedor proveedor) throws DataAccessException{

		proveedorRepo.delete(proveedor);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Proveedor proveedorBorrar = findProveedorById(id).get();
		deleteProveedor(proveedorBorrar);
	}
	@Transactional(readOnly=true)
	public Optional<Proveedor> findProveedorByUsername(String username) {
		return proveedorRepo.findProveedorByUsername(username);
	}
	@Transactional(readOnly=true)
	public Optional<Proveedor> findProveedorById(Integer id) {
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
	
	@Transactional
	public void actualizarProveedor(Proveedor proveedor) throws DataAccessException {
		proveedorRepo.save(proveedor);
	}
	
	
}
