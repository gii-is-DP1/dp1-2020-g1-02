package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.repository.FacturaRepository;
import org.springframework.samples.petclinic.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Transactional
	public int pedidoCount() {
		return (int) pedidoRepo.count();
	}
	
	@Transactional
	public Iterable<Pedido> findAll() {
		return pedidoRepo.findAll();
	}
	
	public void save(Pedido pedido) {
		pedidoRepo.save(pedido);
	}
	
	public void deletePedido(Pedido pedido) {
		pedidoRepo.delete(pedido);
	}
	
	public boolean cumpleCondicion(Pedido pedido) {
		boolean b=true;
		Integer cantidad = pedido.getCantidadProducto();
		Double precio = Double.valueOf(pedido.getOferta().getPrecioU());
		
		if ((cantidad * precio) >100.0) b=false;
		
		return b;
		
	}

	public Optional<Pedido> findById(Integer id) {
		return pedidoRepo.findById(id);
	}
}
