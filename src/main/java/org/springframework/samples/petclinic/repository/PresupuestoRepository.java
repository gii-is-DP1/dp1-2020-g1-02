package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Presupuesto;

public interface PresupuestoRepository extends CrudRepository<Presupuesto, Integer> {
	
	@Query("SELECT p FROM Presupuesto p WHERE p.servicio.id = :servicio")
	Iterable<Presupuesto> presupuestosByServicio(@Param("servicio") Integer servicio);
	
	@Query("SELECT count(*) FROM Presupuesto p WHERE p.servicio.id = :servicio AND p.estado = 1")
	Integer numeroPresupuestosByServicioConEstadoAceptado(@Param("servicio") Integer servicio);

}
