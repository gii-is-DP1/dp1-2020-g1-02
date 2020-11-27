package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.repository.FacturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturaService {
	
	@Autowired
	private FacturaRepository facturaRepo;
	
	@Transactional
	public int facturaCount() {
		return (int) facturaRepo.count();
	}
	
	@Transactional
	public Iterable<Factura> findAll() {
		return facturaRepo.findAll();
	}
	
	public void save(Factura factura) {
		facturaRepo.save(factura);
	}
	
	public void deleteFactura(Factura factura) {
		facturaRepo.delete(factura);
	}
	
	public void deleteById(Integer id) {
		Factura facturaBorrar = findFacturaById(id).get();
		deleteFactura(facturaBorrar);
	}

	public Iterable<Factura> findAllById(Iterable<Integer> ids) {
		return facturaRepo.findAllById(ids);
	}
	
	public Iterable<Factura> findFacturaByProveedorId(Integer idProveedor) {
		List<Integer> iterable = new ArrayList<Integer>();
		iterable.add(idProveedor);
		Iterable<Integer> idI = iterable;
		return facturaRepo.findAllById(idI);
	}
	
	public Optional<Factura> findFacturaById(Integer id) {
		return facturaRepo.findById(id);
	}
}
