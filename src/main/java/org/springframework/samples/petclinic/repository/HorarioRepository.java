package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Horario;

public interface HorarioRepository extends CrudRepository<Horario, Integer> {
	
//	@Query("SELECT h FROM Horario h WHERE h.trabajador.name = ?1")
//	Iterable<Horario> findHorarioByTrabajadorId(String nameTrab);

}
