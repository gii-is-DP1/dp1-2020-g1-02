package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Producto;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= ProductoController.class,
includeFilters= @ComponentScan.Filter(value = Producto.class, type = FilterType.ASSIGNABLE_TYPE ),
excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class ProductoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProductoService productoService;
	
	private Producto producto;
	
	@BeforeEach
	void setup() {
		producto = new Producto();
		producto.setId(1);
		producto.setName("Milagrito");
		producto.setCantidad(0);
		
		given(this.productoService.findAll()).willReturn(Lists.list(producto));
		given( productoService.findProductoById(1)).willReturn(Optional.of(producto));
//		List<Oferta> ofertas = new ArrayList<Oferta>();
//		ofertas.add(oferta);
//		given(this.ofertaService.findAll()).willReturn(ofertas);
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoOfertas() throws Exception{
		mockMvc.perform(get("/productos")).andExpect(status().isOk())
		.andExpect(model().attributeExists("producto"))
		.andExpect(view().name("productos/listadoProductos"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception{
		mockMvc.perform(get("/productos/new")).andExpect(status().isOk()).andExpect(model().attributeExists("producto"))
		.andExpect(view().name("productos/newProducto"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/productos/save")
						.with(csrf())
						.param("name", "Milagrito")
						.param("cantidad", "0"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/productos"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/productos/save")
						.with(csrf())
						.param("name", "")
						.param("cantidad", "0"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("producto"))
			.andExpect(model().attributeHasFieldErrors("producto", "name"))
			.andExpect(view().name("productos/newProducto"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testRestarStock() throws Exception{
		mockMvc.perform(get("/productos/{productoId}/restar", 1))
		.andExpect(status().is3xxRedirection())
//		.andExpect(model().attributeExists("message")
		.andExpect(view().name("redirect:/productos"));
	}
	

}