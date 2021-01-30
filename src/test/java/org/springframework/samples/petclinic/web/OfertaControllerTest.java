package org.springframework.samples.petclinic.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Instalacion;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.InstalacionService;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= OfertaController.class,
includeFilters= @ComponentScan.Filter(value = Oferta.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class OfertaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OfertaService ofertaService;
	@MockBean
	private ProductoService productoService;
	@MockBean
	private ProveedorService provService;
	
	private Oferta oferta;
	private Producto producto;
	private Proveedor proveedor;
	
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
		
		given(this.ofertaService.findOfertaById(1)).willReturn(Optional.of(oferta));
		given(this.productoService.findByName("Fregona")).willReturn(Optional.of(producto));
		given(this.provService.findProveedorById(1)).willReturn(Optional.of(proveedor));
		
		List<Oferta> ofertas = new ArrayList<Oferta>();
		ofertas.add(oferta);
		given(this.ofertaService.findAll()).willReturn(ofertas);
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoOfertas() throws Exception{
		mockMvc.perform(get("/ofertas")).andExpect(status().isOk())
		.andExpect(model().attributeExists("ofertas"))
		.andExpect(view().name("ofertas/listadoOfertas"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception{
		mockMvc.perform(get("/ofertas/new")).andExpect(status().isOk()).andExpect(model().attributeExists("oferta", "proveedores", "productos"))
		.andExpect(view().name("ofertas/editOferta"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/ofertas/save")
						.with(csrf())
						.param("name", "Fregona")
						.param("precioU", "4.05")
						.param("proveedor", "1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/ofertas"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/ofertas/save")
						.with(csrf())
						.param("name", "Fregona")
						.param("precioU", "")
						.param("proveedor", "1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("oferta"))
			.andExpect(model().attributeHasFieldErrors("oferta", "precioU"))
			.andExpect(view().name("ofertas/editOferta"));
	}

}
