package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Servicio;

public interface ServicioRepository extends CrudRepository<Servicio, Integer> {

	@Query("SELECT s FROM Servicio s WHERE s.cliente.id = :cliente")
	Iterable<Servicio> serviciosByCliente(@Param("cliente") Integer cliente);
	
	@Query("SELECT t.servicios FROM Trabajador t WHERE t.id=:trabajador")
	Iterable<Servicio> serviciosByTrabajador(@Param("trabajador") Integer trabajador);

//	@Modifying
//	@Query("DELETE s.trabajadores FROM Servicio s WHERE s.id = :sid")
//	void vaciarListaTrabajadores(@Param("sid") Integer sid);

	
	
}
