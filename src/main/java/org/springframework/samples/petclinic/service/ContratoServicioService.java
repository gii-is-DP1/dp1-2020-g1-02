package org.springframework.samples.petclinic.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.ContratoServicio;
import org.springframework.samples.petclinic.repository.ContratoServicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContratoServicioService {
	
	@Autowired
	private ContratoServicioRepository contratoServicioRepo;
	
	@Transactional
	public int contratoServicioCount() {
		return (int) contratoServicioRepo.count();
	}
	
	@Transactional
	public Iterable<ContratoServicio> findAll() {
		return contratoServicioRepo.findAll();
	}
	
	public void save(ContratoServicio contratoServicio) {
		contratoServicioRepo.save(contratoServicio);
	}
	
	public void delete(ContratoServicio contratoServicio) {
		contratoServicioRepo.delete(contratoServicio);
	}
	
	public Iterable<ContratoServicio> buscaMorosos() {
		return contratoServicioRepo.buscaMorosos();
	}
	
	public Iterable<ContratoServicio> contratosQueCaducanEsteMes(){
		LocalDate now = LocalDate.now();
		return contratoServicioRepo.contratosQueCaducanEsteMes(now.getDayOfMonth(), now.getMonthValue(), now.getYear());
	}

}
