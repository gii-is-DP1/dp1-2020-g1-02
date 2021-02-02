package org.springframework.samples.petclinic.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.repository.HorarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HorarioService {
	@Autowired
	private HorarioRepository horarioRepo;
	
	@Transactional(readOnly=true)
	public int horarioCount() {
		return (int) horarioRepo.count();
	}
	
	@Transactional(readOnly=true)
	public Iterable<Horario> findAll(){
		return horarioRepo.findAll();
	}
	
	@Transactional
	public void save(Horario horario) {
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
	
	@Transactional
	public void deleteById(Integer id) {
		Horario horarioBorrar = findHorarioById(id).get();
		delete(horarioBorrar);
	}

}
