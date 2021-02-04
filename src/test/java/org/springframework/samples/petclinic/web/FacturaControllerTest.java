package org.springframework.samples.petclinic.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.when;
import static org.mockito.BDDMockito.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Factura;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.FacturaService;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= FacturaController.class,
includeFilters= @ComponentScan.Filter(value = Factura.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class FacturaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FacturaService facturaService;
	@MockBean
	private ProveedorService provService;

	private Factura factura;
	private Producto producto;
	private Proveedor proveedor;
	private Oferta oferta;
	private Pedido pedido;
	
	@BeforeEach
	void setup() {
		producto = new Producto();
		producto.setId(1);
		producto.setName("Fregona");
		producto.setCantidad(5);
		
		proveedor = new Proveedor();
		proveedor.setId(1);
		proveedor.setName("Lejias SL");
		proveedor.setTelefono("666555444");
		proveedor.setEmail("lejiassl@gmail.com");
		proveedor.setDireccion("calle rodolfo, n12");
		
		oferta = new Oferta();
		oferta.setId(1);
		oferta.setPrecioU("2.35");
		oferta.setProducto(producto);
		oferta.setProveedor(proveedor);
		
		pedido = new Pedido();
		pedido.setCantidadProducto(5);
		pedido.setFechaPedido(LocalDate.now());
		pedido.setId(1);
		pedido.setOferta(oferta);
		
		factura = new Factura();
		factura.setFecha(LocalDate.now());
		factura.setId(1);
		factura.setPedido(pedido);
		factura.setPrecio_total(11.75);
		factura.setProveedor(proveedor);
		
		given(this.provService.findProveedorById(1)).willReturn(Optional.of(proveedor));
		given(this.facturaService.findFacturaById(1)).willReturn(Optional.of(factura));
		given(this.facturaService.findAll()).willReturn(Lists.list(factura));
		given(this.facturaService.findFacturaByProveedorName(any())).willReturn(Lists.list(factura));
	}

	
	@WithMockUser(value = "spring")
	@Test
	void testListadoFacturas() throws Exception{
		mockMvc.perform(get("/facturas")).andExpect(status().isOk()).andExpect(model().attributeExists("facturas"))
		.andExpect(view().name("facturas/listadoFacturas"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testVerFactura() throws Exception{
		mockMvc.perform(get("/facturas/{facturaId}", 1)).andExpect(status().isOk())
		.andExpect(model().attributeExists("factura"))
		.andExpect(model().attribute("factura", hasProperty("fecha", is(LocalDate.now()))))
		.andExpect(model().attribute("factura", hasProperty("precio_total", is(11.75))))
		.andExpect(model().attribute("factura", hasProperty("proveedor", is(proveedor))))
		.andExpect(model().attribute("factura", hasProperty("pedido", is(pedido))))
		.andExpect(view().name("facturas/verFactura"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testFiltrarFactura() throws Exception{
		mockMvc.perform(get("/facturas/filtrado").param("nameProv", "Lejias"))

		.andExpect(status().isOk())

		.andExpect(model().attributeExists("filtrado"))
		.andExpect(model().attributeExists("facturas"))
		.andExpect(view().name("facturas/listadoFacturas"));
	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testListadoFacturasPorProveedor() throws Exception{
//		mockMvc.perform(get("/facturas/misFacturas"))
//		.andExpect(status().isOk())
//		.andExpect(model().attributeExists("facturas"))
//		.andExpect(view().name("facturas/listadoFacturas"));
//	}
	
	


}
