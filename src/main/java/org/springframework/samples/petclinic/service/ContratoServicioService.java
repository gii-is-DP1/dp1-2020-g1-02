package org.springframework.samples.petclinic.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.ContratoServicio;
import org.springframework.samples.petclinic.repository.ContratoServicioRepository;
import org.springframework.samples.petclinic.model.ContratoTrabajador;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.repository.ContratoServicioRepository;
import org.springframework.samples.petclinic.repository.ServicioRepository;
import org.springframework.samples.petclinic.service.exceptions.SolapamientoFechasException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContratoServicioService {
	
	
	private ContratoServicioRepository contratoServicioRepo;
	
	@Autowired
	public ContratoServicioService(ContratoServicioRepository contratoServicioRepo) {
		super();
		this.contratoServicioRepo = contratoServicioRepo;
	}

	@Transactional
	public int contratoServicioCount() {
		return (int) contratoServicioRepo.count();
	}
	
	@Transactional
	public Iterable<ContratoServicio> findAll() {
		return contratoServicioRepo.findAll();
	}
	
	@Transactional
	public void save(ContratoServicio contratoServicio) {
		contratoServicioRepo.save(contratoServicio);
	}
	@Transactional
	public void delete(ContratoServicio contratoServicio) {
		contratoServicioRepo.delete(contratoServicio);
	}
	
	@Transactional(readOnly=true)
	public Iterable<ContratoServicio> buscaMorosos() {
		return contratoServicioRepo.buscaMorosos();
	}
	
	@Transactional(readOnly=true)
	public Iterable<ContratoServicio> contratosQueCaducanEsteMes(){
		LocalDate now = LocalDate.now();
		return contratoServicioRepo.contratosQueCaducanEsteMes(now.getDayOfMonth(), now.getMonthValue(), now.getYear());
	}

	@Transactional(readOnly=true)
	public Iterable<ContratoServicio> contratosByIdCliente(int idCliente) {
		return contratoServicioRepo.contratosByIdCliente(idCliente);
	}
	
	@Transactional(rollbackFor = SolapamientoFechasException.class)
	public void crearContrato(ContratoServicio contratoServicio) throws SolapamientoFechasException {
		LocalDate inicio = contratoServicio.getFechainicial();
		LocalDate fin = contratoServicio.getFechafinal();
		Integer idServicio = contratoServicio.getPresupuesto().getServicio().getId();
		Integer a = contratoServicioRepo.contratosServiciosSolapadosPorFechaIntroducida(idServicio, inicio, fin);
		if(a == 0) {
			this.save(contratoServicio);
		} else {
			throw new SolapamientoFechasException();
		}
	}
}
