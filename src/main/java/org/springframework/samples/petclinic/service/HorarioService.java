package org.springframework.samples.petclinic.service;

import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.repository.HorarioRepository;
import org.springframework.samples.petclinic.service.exceptions.SolapamientoFechasException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HorarioService {

	private HorarioRepository horarioRepo;
	
	@Autowired
	public HorarioService(HorarioRepository horarioRepo) {
		super();
		this.horarioRepo = horarioRepo;
	}

	@Transactional(readOnly=true)
	public int horarioCount() {
		return (int) horarioRepo.count();
	}
	
	@Transactional(readOnly=true)
	public Iterable<Horario> findAll(){
		return horarioRepo.findAll();
	}
	
	@Transactional(rollbackFor = ValidationException.class)
	public void save(Horario horario) throws ValidationException {
		horarioRepo.save(horario);
	}
	
	@Transactional
	public void delete(Horario horario) {
		horarioRepo.delete(horario);
	}
	
	@Transactional(readOnly=true)
	public Optional<Horario> findHorarioById(Integer horarioId) {
		return horarioRepo.findById(horarioId);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Horario> findHorarioByTrabajadorName(String name) {
		return horarioRepo.findHorariosByTrabajadorName(name);
	}
	
	@Transactional(readOnly=true)
	public Iterable<Horario> findHorarioByTrabajadorId(Integer id) {
		return horarioRepo.findHorariosByTrabajadorId(id);
	}
	
	@Transactional(readOnly=true)
	public Integer findHorasSolapadas(Horario horario) {
		return horarioRepo.findHorasSolapadas(horario.getTrabajador().getId(), horario.getHora_inicio(), horario.getHora_fin(), horario.getFecha());
	}
	
	@Transactional(rollbackFor = SolapamientoFechasException.class)
	public void solapamientoHorasEnHorarioTrabajador(Horario horario) throws SolapamientoFechasException {
		if(this.findHorasSolapadas(horario) != 0) {
			throw new SolapamientoFechasException();
		}
	}
	
	@Transactional
	public void crearHorario(Horario horario) throws SolapamientoFechasException, ValidationException {
		this.solapamientoHorasEnHorarioTrabajador(horario);
		this.save(horario);
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Horario horarioBorrar = findHorarioById(id).get();
		delete(horarioBorrar);
	}

}
