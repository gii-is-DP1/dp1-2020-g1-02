package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.ContratoTrabajador;
import org.springframework.samples.petclinic.repository.ContratoTrabajadorRepository;
import org.springframework.samples.petclinic.service.exceptions.SolapamientoFechasException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContratoTrabajadorService {

	private ContratoTrabajadorRepository contratoTrabajadorRepo;
	
	@Autowired
	public ContratoTrabajadorService(ContratoTrabajadorRepository contratoTrabajadorRepo) {
		super();
		this.contratoTrabajadorRepo = contratoTrabajadorRepo;
	}
	
	@Transactional
	public int contratoTrabajadorCount() {
		return (int) contratoTrabajadorRepo.count();
	}

	@Transactional
	public Iterable<ContratoTrabajador> findAll(){
		return contratoTrabajadorRepo.findAll();
	}
	
	@Transactional
	public void save(ContratoTrabajador contratoTrabajador) {
		contratoTrabajadorRepo.save(contratoTrabajador);
	}
	
	@Transactional
	public void delete(ContratoTrabajador contratoTrabajador) {
		contratoTrabajadorRepo.delete(contratoTrabajador);
	}

	@Transactional(readOnly=true)
	public Optional<ContratoTrabajador> findContratoTrabajadorById(int contratoId) {
		return contratoTrabajadorRepo.findById(contratoId);
	}
	
	@Transactional(readOnly=true)
	public Iterable<ContratoTrabajador> contratosTrabajdorQueCaducanEsteMes(){
		LocalDate now = LocalDate.now();
		return contratoTrabajadorRepo.contratosTrabajadorQueCaducanEsteMes(now.getDayOfMonth(), now.getMonthValue(), now.getYear());
	}
  
	@Transactional(rollbackFor = SolapamientoFechasException.class)
	public void crearContrato(ContratoTrabajador contratoTrabajador) throws SolapamientoFechasException {
		Integer idTrabajador = contratoTrabajador.getTrabajador().getId();
		LocalDate inicio = contratoTrabajador.getFechainicial();
		LocalDate fin = contratoTrabajador.getFechafinal();
		Integer a = contratoTrabajadorRepo.contratosTrabajadorSolapadosPorFechaIntroducida(idTrabajador, inicio, fin);
		if(a == 0) {
			this.save(contratoTrabajador);
		} else {
			throw new SolapamientoFechasException();
		}
	}

	@Transactional(readOnly=true)
	public Iterable<ContratoTrabajador> findContratoTrabajadorByTrabajador(Integer trabajador){
		return contratoTrabajadorRepo.contratosTrabajadorPorTrabajador(trabajador);
	}
}
