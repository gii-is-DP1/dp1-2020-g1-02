package org.springframework.samples.petclinic.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.assertj.core.util.Lists;
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
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= PedidoController.class,
includeFilters= @ComponentScan.Filter(value = Pedido.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class PedidoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PedidoService pedidoService;
	@MockBean
	private OfertaService ofertaService;
	@MockBean
	private FacturaService facturaService;
	@MockBean
	private ProductoService productoService;
	
	@MockBean
	private EntityManager entityManager;
	
	
	
	private Pedido pedido;
	private Oferta oferta;
	private Factura factura;
	private Proveedor proveedor;
	private Producto producto;
	
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
        oferta.setName(producto.getName());
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

		given(this.pedidoService.findAll()).willReturn(Lists.list(pedido));
		given(this.ofertaService.findOfertaById(1)).willReturn(Optional.of(oferta));
		given(this.productoService.findByName(pedido.getOferta().getName())).willReturn(Optional.of(producto));
		
		given(this.entityManager.find(Oferta.class, 1)).willReturn(oferta);
		
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoPedidos() throws Exception{
		mockMvc.perform(get("/pedidos")).andExpect(status().isOk())
		.andExpect(model().attributeExists("pedidos"))
		.andExpect(view().name("administradores/listadoPedidos"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNewPedido() throws Exception{
		mockMvc.perform(get("/pedidos/new/{oId}", 1)).andExpect(status().isOk())
		.andExpect(model().attributeExists("pedido"))
		.andExpect(model().attribute("pedido", hasProperty("oferta", is(oferta))))
		.andExpect(view().name("administradores/editPedido"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/pedidos/save")
						.with(csrf())
						.param("cantidadProducto", "5")
						.param("fechaPedido", "2021/01/31")
						.param("oferta", "1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/pedidos"));
	}

	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/pedidos/save")
						.with(csrf())
						.param("cantidadProducto", "")
						.param("fechaPedido", "2021/01/31")
						.param("oferta", "1"))
		.andExpect(model().attributeHasErrors("pedido"))
		.andExpect(model().attributeHasFieldErrors("pedido", "cantidadProducto"))
		.andExpect(status().isOk())
		.andExpect(view().name("administradores/editPedido"));
	}
	
//	@WithMockUser(value = "spring")
//    @Test
//    void testSuperiorA100() throws Exception {
//		mockMvc.perform(post("/pedidos/save")
//						.with(csrf())
//						.param("cantidadProducto", "1000")
//						.param("fechaPedido", "2021/01/31")
//						.param("oferta", "1"))
//		.andExpect(status().is4xxClientError())
//		.andExpect(view().name("administradores/editPedido"));
//	}

}