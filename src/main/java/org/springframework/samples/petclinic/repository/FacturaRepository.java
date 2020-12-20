package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Factura;

public interface FacturaRepository extends CrudRepository<Factura, Integer>{

	
//	@Query("SELECT DISTINCT * FROM FACTURAS WHERE proveedor_id = ?1")
}
