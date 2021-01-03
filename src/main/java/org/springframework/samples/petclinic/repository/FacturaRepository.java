package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Factura;

public interface FacturaRepository extends CrudRepository<Factura, Integer>{

//	@Query("SELECT f FROM Factura f WHERE f.proveedor.name LIKE ':nameProv%'")
//	Iterable<Factura> findFacturasByProveedorName(@Param("nameProv") String nameProv);
	
	
//	@Query("SELECT f FROM Factura f  INNER JOIN Proveedor p ON f.proveedor = p.id  WHERE p.name LIKE ':nameProv%' ")
//	Iterable<Factura> findFacturasByProveedorName(@Param("nameProv") String nameProv);
	
	@Query("SELECT f FROM Factura AS f INNER JOIN Proveedor AS p  ON f.proveedor = p.id  WHERE p.name LIKE ':nameProv%' ")
	Iterable<Factura> findFacturasByProveedorName(@Param("nameProv") String nameProv);
}
