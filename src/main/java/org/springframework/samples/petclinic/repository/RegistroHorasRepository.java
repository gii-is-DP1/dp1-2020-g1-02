package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.RegistroHoras;

public interface RegistroHorasRepository extends CrudRepository<RegistroHoras, Integer> {
	
	@Query("SELECT r FROM RegistroHoras r WHERE r.trabajador.id = :tId")
	Iterable<RegistroHoras> findRegistroHorasByTrabajadorId(Integer tId);
	
	@Query("Select sum(DATEDIFF(hour, r.hora_inicio, r.hora_fin)) FROM RegistroHoras r WHERE r.trabajador.id = :tId")
	Double horasTotalesByTrabajador(Integer tId);

}
