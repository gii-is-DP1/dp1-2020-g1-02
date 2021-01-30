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
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= ProveedorController.class,
			includeFilters= @ComponentScan.Filter(value = Proveedor.class, type = FilterType.ASSIGNABLE_TYPE ),
			excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
			excludeAutoConfiguration= SecurityConfiguration.class)

public class ProveedorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProveedorService provService;
	
	private Proveedor proveedor;
	
	@BeforeEach
	void setup() {
		proveedor = new Proveedor();
		proveedor.setId(1);
		proveedor.setName("Pablo");
		proveedor.setTelefono("633444555");
		proveedor.setEmail("pablog@gmail.com");
		proveedor.setDireccion("Calle Conventual 17");
		
		given(this.provService.findProveedorById(1)).willReturn(Optional.of(proveedor));
		
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		proveedores.add(proveedor);
		given(this.provService.findAll()).willReturn(proveedores);
	}

	@WithMockUser(value = "spring")
	@Test
	void testListadoProveedores() throws Exception{
		mockMvc.perform(get("/proveedores")).andExpect(status().isOk())
		.andExpect(model().attributeExists("prov"))
		.andExpect(view().name("proveedores/listadoProv"));
	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testInitCreationForm() throws Exception{
//		mockMvc.perform(get("/proveedores/new")).andExpect(status().isOk()).andExpect(model().attributeExists("proveedor"))
//		.andExpect(view().name("instalaciones/newInstalacion"));
//	}
	
//	@WithMockUser(value = "spring")
//    @Test
//    void testProcessCreationFormSuccess() throws Exception {
//		mockMvc.perform(post("/instalaciones/save")
//						.with(csrf())
//						.param("dimension", "120.3")
//						.param("lugar", "Calle Río Danubio 23"))
//			.andExpect(status().is2xxSuccessful())
//			.andExpect(view().name("instalaciones/listadoInstalaciones"));
//	}
	
//	@WithMockUser(value = "spring")
//    @Test
//    void testProcessCreationFormHasErrors() throws Exception {
//		mockMvc.perform(post("/instalaciones/save")
//						.with(csrf())
//						.param("dimension", "")
//						.param("lugar", "Calle Santa Maria"))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeHasErrors("instalacion"))
//			.andExpect(model().attributeHasFieldErrors("instalacion", "dimension"))
//			.andExpect(view().name("instalaciones/newInstalacion"));
//	}
	
}
