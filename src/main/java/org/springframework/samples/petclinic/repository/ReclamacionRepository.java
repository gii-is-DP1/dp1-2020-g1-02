package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Reclamacion;

	
public interface ReclamacionRepository extends CrudRepository<Reclamacion, Integer> {
	
	@Query("SELECT r FROM Reclamacion r WHERE r.servicio.name = ?1")
	Iterable<Reclamacion> findReclamacionesByServicioId(String nameServ);
}


