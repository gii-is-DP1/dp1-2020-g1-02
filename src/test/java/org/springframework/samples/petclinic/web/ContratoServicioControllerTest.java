package org.springframework.samples.petclinic.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.ContratoServicio;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Presupuesto;
import org.springframework.samples.petclinic.model.Proveedor;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.TipoPresupuesto;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ContratoServicioService;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.PresupuestoService;
import org.springframework.samples.petclinic.service.ProductoService;
import org.springframework.samples.petclinic.service.ProveedorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= ContratoServicioController.class,
			includeFilters= @ComponentScan.Filter(value = ContratoValidator.class, type = FilterType.ASSIGNABLE_TYPE ),
			excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
			excludeAutoConfiguration= SecurityConfiguration.class)

public class ContratoServicioControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClienteService clienteService;
	@MockBean
	private ContratoServicioService contratoServicioService;
	@MockBean
	private PresupuestoService presupuestoService;
	@MockBean
	private EntityManager entityManager;
	
	private ContratoServicio cS;
	private Presupuesto prep;
	private Servicio serv;
	private Cliente cliente;
	
	@BeforeEach
	void setup() {
		//Preparacion del contratoServicio
		cS = new ContratoServicio();
		cS.setId(1);
		cS.setFechainicial(LocalDate.of(2019, 10, 03));
		cS.setFechafinal(LocalDate.of(2019, 11, 03));
		cS.setFechapago(LocalDate.of(2019, 10, 30));
		cS.setPeriodoPrueba(true);
		cS.setPresupuesto(prep);
		
		prep = new Presupuesto();
		prep.setId(1);
		prep.setContrato(cS);
		prep.setEstado(EstadoServicio.Aceptado);
		prep.setPrecio(15.23);
		prep.setServicio(serv);
		prep.setTipopresupuesto(TipoPresupuesto.Cerrado);
		
		serv = new Servicio();
		serv.setId(1);
		serv.setCliente(cliente);
		serv.setEstado(EstadoServicio.Aceptado);
		serv.setFechainicio(LocalDate.of(2019, 05, 12));
		serv.setFechafin(LocalDate.of(2019, 06, 14));
		serv.setLugar("Sevilla");
		serv.setTipocategoria(TipoCategoria.Limpieza);
		
		cliente = new Cliente();
		cliente.setId(1);
		cliente.setDni("46325489L");
		cliente.setNombre("José");
		cliente.setApellidos("Rodríguez Cáceres");
		cliente.setCorreo("joserc@gmail.com");
		cliente.setDireccion("Calle Santana 12");
		cliente.setTelefono("653793578");
		
		//given(this.contratoServicioService.findAll()).willReturn(Optional.of(cS));
		given(this.presupuestoService.findPresupuestoById(1)).willReturn(Optional.of(prep));
		given(this.clienteService.findClienteById(1)).willReturn(Optional.of(cliente));
		given(this.entityManager.find(ContratoServicio.class, 1)).willReturn(cS);
		given(this.entityManager.find(Cliente.class, 1)).willReturn(cliente);
		
		given(this.clienteService.findClienteByUsername(any())).willReturn(Optional.of(cliente));
		//given(this.userService.getLoggedUser()).willReturn(user);
//		((BDDMockito) when(this.user.getAuthorities().getAuthority().equalsIgnoreCase("proveedor"))).willReturn(true);
		
		
		
		
		List<ContratoServicio> cServs = new ArrayList<ContratoServicio>();
		cServs.add(cS);
		given(this.contratoServicioService.findAll()).willReturn(cServs);
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoContratoServicios() throws Exception{
		mockMvc.perform(get("/contratosServicios"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("contratosServicios"))
		.andExpect(view().name("contratosServicios/listadoContratosServicios"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNewContratoServicio() throws Exception{
		mockMvc.perform(get("/contratosServicios/{pId}/new", 1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("contratoServicio"))
		.andExpect(model().attribute("contratoServicio", hasProperty("presupuesto", is(prep))))
		.andExpect(view().name("contratosServicios/editContratoServicio"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/contratosServicios/save")
						.with(csrf())
						.param("fechainicial", "2020/11/17")
						.param("fechafinal", "2020/12/10")
						.param("fechapago", "2020/12/15")
						.param("periodoPrueba", "true")
						.param("presupuesto", "1")
						.param("id", "1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/contratos"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/contratosServicios/save")
						.with(csrf())
						.param("fechainicial", "2020/11/19")
						.param("fechafinal", "2020/12/19")
						.param("fechapago", "2020/12/15")
						.param("periodoprueba", "true")
						.param("presupuesto", "1")
						.param("id", ""))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasErrors("contratoServicio"))
			.andExpect(model().attributeHasFieldErrors("contratoServicio", "id"))
			.andExpect(view().name("contratosServicios/editContratoServicio"));
	}
}
