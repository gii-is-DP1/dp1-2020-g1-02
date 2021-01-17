package org.springframework.samples.petclinic.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.repository.PedidoRepository;
import org.springframework.samples.petclinic.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PedidoServiceTest {

	
	@Mock
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ProductoService productoService;
	
	
	@BeforeEach
	void setup() {
		
		Oferta o = new Oferta();
		o.setPrecioU("20.0");
		o.setProducto(productoService.findProductoById(2).get());
		Pedido ped = new Pedido();
		ped.setOferta(o);
		ped.setCantidadProducto(6);
		when(pedidoRepo.findById(2).get()).thenReturn(ped);
		
	}
	
//	@Test
//	public void testCumpleCondicion() {ç
//		
//		Pedido pedido = pedidoService.findById(1).get().getOferta().getPrecioU();
//		boolean b = pedidoService.cumpleCondicion(pedido);
//	}
}
