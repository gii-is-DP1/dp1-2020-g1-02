package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Valoracion;

public interface ValoracionRepository extends CrudRepository<Valoracion, Integer>{

	@Query("SELECT DISTINCT v FROM Valoracion v LEFT JOIN FETCH v.cliente WHERE lower(v.cliente.nombre) LIKE :nombreCli%")
	Collection<Valoracion> findAllByClienteName(@Param("nombreCli") String nombreCli);

}
