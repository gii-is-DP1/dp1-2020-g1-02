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
	
	@Transactional
	public int horarioCount() {
		return (int) horarioRepo.count();
	}
	
	@Transactional
	public Iterable<Horario> findAll(){
		return horarioRepo.findAll();
	}

	public void save(Horario horario) {
		horarioRepo.save(horario);
	}
	
	public void delete(Horario horario) {
		horarioRepo.delete(horario);
		
	}

	public Optional<Horario> findHorarioById(Integer horarioId) {
		return horarioRepo.findById(horarioId);
	}
	
	public Iterable<Horario> findHorarioByTrabajadorName(String name) {
		return horarioRepo.findHorariosByTrabajadorName(name);
	}
	
	public void deleteById(Integer id) {
		Horario horarioBorrar = findHorarioById(id).get();
		delete(horarioBorrar);
	}

}
