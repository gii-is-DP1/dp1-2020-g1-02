package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Factura;

public interface FacturaRepository extends CrudRepository<Factura, Integer>{

	@Query("SELECT f FROM Factura f WHERE f.proveedor.name = ?1")
	Iterable<Factura> findFacturasByProveedorId(String nameProv);
}
