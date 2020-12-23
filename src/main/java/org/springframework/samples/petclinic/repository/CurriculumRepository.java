package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Curriculum;

public interface CurriculumRepository extends CrudRepository<Curriculum, Integer> {
	
	@Query("SELECT c FROM Curriculum c WHERE c.trabajador.name = ?1")
	Iterable<Curriculum> findCurriculumByTrabajadorId(String nameTrab);

}
