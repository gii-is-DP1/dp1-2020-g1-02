package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.repository.PedidoRepository;
import org.springframework.samples.petclinic.service.exceptions.LimitePedidoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private ProductoService productoService;
	@Autowired
	private FacturaService facturaService;
	
	@Transactional(readOnly=true)
	public int pedidoCount() {
		return (int) pedidoRepo.count();
	}
	
	@Transactional(readOnly=true)
	public Iterable<Pedido> findAll() {
		return pedidoRepo.findAll();
	}
	@Transactional
	public void save(Pedido pedido) {
		pedidoRepo.save(pedido);
	}
	@Transactional
	public void deletePedido(Pedido pedido) {
		pedidoRepo.delete(pedido);
	}
	
	public boolean pedidoMenor200(Pedido pedido) {
		boolean b=true;
		Integer cantidad = pedido.getCantidadProducto();
		Double precio = Double.valueOf(pedido.getOferta().getPrecioU());
		
		if ((cantidad * precio) >200.0) b=false;
		
		return b;
		
	}
	
	@Transactional(rollbackFor = LimitePedidoException.class)
	public void crearPedido(Pedido pedido) throws LimitePedidoException {
		if(this.pedidoMenor200(pedido)) {
    		pedido.setFechaPedido(LocalDate.now());
    		this.save(pedido);
    		Producto producto = productoService.findByName(pedido.getOferta().getName()).get();
    		productoService.sumarProducto(producto, pedido.getCantidadProducto());
    		facturaService.creaFactura(pedido);
		} else {
			throw new LimitePedidoException();
		}
	}
	@Transactional(readOnly=true)
	public Optional<Pedido> findById(Integer id) {
		return pedidoRepo.findById(id);
	}
}
