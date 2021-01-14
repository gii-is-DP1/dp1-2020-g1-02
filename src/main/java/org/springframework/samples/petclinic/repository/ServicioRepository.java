package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Servicio;

public interface ServicioRepository extends CrudRepository<Servicio, Integer> {

	@Query("SELECT s FROM Servicio s WHERE s.cliente.id = :cliente")
	Iterable<Servicio> serviciosByCliente(@Param("cliente") Integer cliente);
	
}
