package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.validation.ConstraintViolationException;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PedidoServiceTest {
	
	@Mock
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PedidoService pedidoService;
	
	private Pedido pedido;
	private Oferta oferta;
	private Proveedor p;
	private Producto producto;
	
	@BeforeEach
	void setup() {
		p=new Proveedor();
		p.setId(1);
		p.setName("Pablo");
		p.setTelefono("633444555");
		p.setEmail("pablog@gmail.com");
		p.setDireccion("Calle Conventual 17");
		p.setFacturas(Lists.list(new Factura()));
		
		oferta = new Oferta();
		oferta.setId(1);
		oferta.setName("lejia");
		oferta.setPrecioU(2.85);
		oferta.setProveedor(p);
		
		producto = new Producto();
		producto.setId(1);
		producto.setCantidad(0);
		producto.setName("lejia");
		producto.setOfertas(Lists.list(oferta));
		
		pedido = new Pedido();
		pedido.setId(1);
		pedido.setCantidadProducto(5);
		pedido.setFechaPedido(LocalDate.now());
		pedido.setOferta(oferta);
		
	}
	
	//Test positivos
	
	@Test
	public void testExistenPedidos() {
		int count = pedidoService.pedidoCount();
		assertNotEquals(0, count);
	}
	
	@Test
	public void testGuardarPedido() {
		pedidoService.save(pedido);
		assertEquals(3, pedidoService.pedidoCount());
	}
	
	@Test
	public void testBorrarOfertaById() {
		Pedido pedidoBorrar = pedidoService.findById(1).get();
		pedidoService.deletePedido(pedidoBorrar);
		assertEquals(false, pedidoService.findById(1).isPresent());
	}
	
	//Test negativos
	
	@Test
	public void testPedidoNoExistente() {
		assertEquals(false, pedidoService.findById(100).isPresent());
	}
	
}
