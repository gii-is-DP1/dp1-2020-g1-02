package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.RegistroHoras;

public interface RegistroHorasRepository extends CrudRepository<RegistroHoras, Integer> {
	
	@Query("SELECT r FROM RegistroHoras r WHERE r.trabajador.name = ?1")
	Iterable<RegistroHoras> findRegistroHorasByTrabajadorId(String nameTrab);

}
