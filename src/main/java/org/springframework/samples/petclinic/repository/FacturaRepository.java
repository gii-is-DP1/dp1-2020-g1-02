package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Factura;

public interface FacturaRepository extends CrudRepository<Factura, Integer>{

	
	@Query("SELECT DISTINCT f FROM Factura f LEFT JOIN FETCH f.proveedor WHERE lower(f.proveedor.name) LIKE :nameProv%")
	Collection<Factura> findAllByProveedorName(@Param("nameProv") String nameProv);
	
}
