package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Instalacion;

public interface InstalacionRepository extends CrudRepository<Instalacion, Integer> {
	
//	@Query("SELECT i FROM Instalacion i WHERE i.cliente.name = ?1")
//	Iterable<Instalacion> findInstalacionesByClienteId(String nameClient);
	
	@Query("SELECT DISTINCT i FROM Instalacion i LEFT JOIN FETCH i.cliente WHERE i.cliente.nombre LIKE :nombreCli%")
	Collection<Instalacion> findInstalacionesByClienteName(@Param("nombreCli") String nombreCli);

}
