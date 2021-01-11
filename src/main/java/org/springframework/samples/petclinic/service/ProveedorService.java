package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorService {
	
	
	private ProveedorRepository proveedorRepo;
	
	@Autowired
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
}
