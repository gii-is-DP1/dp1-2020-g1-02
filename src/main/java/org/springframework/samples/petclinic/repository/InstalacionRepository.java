package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Instalacion;

public interface InstalacionRepository extends CrudRepository<Instalacion, Integer> {
	
	@Query("SELECT i FROM Instalacion i WHERE i.cliente.name = ?1")
	Iterable<Instalacion> findInstalacionesByClienteId(String nameClient);

}
