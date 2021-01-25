package org.springframework.samples.petclinic.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.repository.FacturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturaService {
	
	@Autowired
	private FacturaRepository facturaRepo;
	
	@Transactional
	public int facturaCount() {
		return (int) facturaRepo.count();
	}
	
	@Transactional
	public Iterable<Factura> findAll() {
		return facturaRepo.findAll();
	}
	
	public void save(Factura factura) {
		facturaRepo.save(factura);
	}
	
	public void deleteFactura(Factura factura) {
		facturaRepo.delete(factura);
	}
	
	public void deleteById(Integer id) {
		Factura facturaBorrar = findFacturaById(id).get();
		deleteFactura(facturaBorrar);
	}

	public Collection<Factura> findFacturaByProveedorName(String name) {
		return facturaRepo.findFacturasByProveedorName(name);
	}
	
	
	public Optional<Factura> findFacturaById(Integer id) {
		return facturaRepo.findById(id);
	}
	
	public Optional<Factura> findFacturaByPedidoID(Integer idP){
		
		
		return null;
		
	}
	
	public void creaFactura(Pedido pedido) {
		
		Proveedor prov = pedido.getOferta().getProveedor();
		LocalDate fecha = pedido.getFechaPedido();
		Double precio = pedido.getCantidadProducto() * Double.valueOf(pedido.getOferta().getPrecioU());
		
		Factura f = new Factura();
		f.setFecha(fecha);
		f.setPedido(pedido);
		f.setPrecio_total(precio);
		f.setProveedor(prov);
		
		facturaRepo.save(f);
	}
}
