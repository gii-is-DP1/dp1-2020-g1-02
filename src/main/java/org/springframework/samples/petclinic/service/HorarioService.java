package org.springframework.samples.petclinic.service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.Trabajador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.HorarioRepository;
import org.springframework.samples.petclinic.service.exceptions.HorarioException;
import org.springframework.samples.petclinic.service.exceptions.LimitePedidoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HorarioService {
	@Autowired
	private HorarioRepository horarioRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TrabajadorService trabajadorService;
	
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
	
	@Transactional(readOnly=true)
	public Iterable<Horario> findHorarioByTrabajadorId(Integer id) {
		return horarioRepo.findHorariosByTrabajadorId(id);
	}
	
	public Integer findHorasSolapadas(Trabajador trabajador, Horario horario) {
		return horarioRepo.findHorasSolapadas(trabajador.getId(), horario.getHora_inicio(), horario.getHora_fin());
	}
	
	
//	public boolean cumpleNoEsLaMismaHora(Horario horario, Trabajador trabajador) {
//		Boolean res = false;
//		Set<Horario> horariosTrabajador = trabajador.getHorarios();
//		Iterator<Horario> iterador = horariosTrabajador.iterator();
//		while(iterador.hasNext()) {
//			LocalDateTime h1 = iterador.next().getHora_inicio();
//			LocalDateTime h2 = iterador.next().getHora_fin();
//			if() {
//				res = true;
//			}
//		}
//		return res;
//	}
	
	@Transactional(rollbackFor = HorarioException.class)
	public void crearHorario(Horario horario, Trabajador trabajador) throws HorarioException {
		if(this.findHorasSolapadas(trabajador, horario) == 0) {
			horario.setTrabajador(trabajador);
			this.save(horario);
		} else {
			throw new HorarioException();
		}
	}
	
	
	@Transactional
	public void deleteById(Integer id) {
		Horario horarioBorrar = findHorarioById(id).get();
		delete(horarioBorrar);
	}

}
