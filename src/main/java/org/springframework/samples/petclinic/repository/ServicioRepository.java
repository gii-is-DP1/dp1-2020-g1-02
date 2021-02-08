package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.Trabajador;



public interface ServicioRepository extends CrudRepository<Servicio, Integer> {

	@Query("SELECT s FROM Servicio s WHERE s.cliente.id = :cliente")
	Iterable<Servicio> serviciosByCliente(@Param("cliente") Integer cliente);
	
	@Query("SELECT t.servicios FROM Trabajador t WHERE t.id=:trabajador")
	Iterable<Servicio> serviciosByTrabajador(@Param("trabajador") Integer trabajador);
	
//	@Query("UPDATE Servicio s SET s.trabajadores=:trabajadores WHERE s.id=:id")
//	void asignarTrabajadores(@Param("id") Integer id, @Param("trabajadores") List<Trabajador> trabajadores) throws DataAccessException;

	
}
