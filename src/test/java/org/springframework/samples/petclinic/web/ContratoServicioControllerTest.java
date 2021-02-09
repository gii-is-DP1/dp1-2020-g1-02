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
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.ContratoServicio;
import org.springframework.samples.petclinic.model.EstadoServicio;
import org.springframework.samples.petclinic.model.Presupuesto;
import org.springframework.samples.petclinic.model.Servicio;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.model.TipoPresupuesto;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ContratoServicioService;
import org.springframework.samples.petclinic.service.PresupuestoService;
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
	@MockBean
	private UserService userService;
	
	private ContratoServicio cS;
	private Presupuesto prep;
	private Servicio serv;
	private Cliente cliente;
	private User user;
	private Authorities authority;
	
	@BeforeEach
	void setup() {
		//Preparacion del contratoServicio
		cS = new ContratoServicio();
		cS.setId(1);
		cS.setFechainicial(LocalDate.of(2019, 10, 03));
		cS.setFechafinal(LocalDate.of(2019, 11, 03));
		cS.setFechapago(LocalDate.of(2019, 10, 30));
		cS.setPeriodoPrueba(true);
		
		authority = new Authorities();
		user = new User();
		authority.setAuthority("cliente");
		authority.setId(1);
		user.setUsername("Ironman");
		authority.setUser(user);
		user.setAuthorities(authority);
		user.setPassword("admin");
		
		prep = new Presupuesto();
		prep.setId(1);
		prep.setContrato(cS);
		prep.setEstado(EstadoServicio.Aceptado);
		prep.setPrecio(15.23);
		prep.setTipopresupuesto(TipoPresupuesto.Cerrado);
		
		cS.setPresupuesto(prep);
		
		serv = new Servicio();
		serv.setId(1);
		serv.setEstado(EstadoServicio.Aceptado);
		serv.setFechainicio(LocalDate.of(2019, 05, 12));
		serv.setFechafin(LocalDate.of(2019, 06, 14));
		serv.setLugar("Sevilla");
		serv.setTipocategoria(TipoCategoria.Limpieza);
		
		prep.setServicio(serv);
		
		cliente = new Cliente();
		cliente.setId(1);
		cliente.setDni("46325489L");
		cliente.setNombre("José");
		cliente.setApellidos("Rodríguez Cáceres");
		cliente.setCorreo("joserc@gmail.com");
		cliente.setDireccion("Calle Santana 12");
		cliente.setTelefono("653793578");
		
		serv.setCliente(cliente);
		
		given(this.presupuestoService.findPresupuestoById(1)).willReturn(Optional.of(prep));
		given(this.clienteService.findClienteById(1)).willReturn(Optional.of(cliente));
		given(this.entityManager.find(ContratoServicio.class, 1)).willReturn(cS);
		given(this.entityManager.find(Cliente.class, 1)).willReturn(cliente);
		given(this.entityManager.find(Presupuesto.class, 1)).willReturn(prep);
		
		given(this.clienteService.findClienteByUsername(any())).willReturn(Optional.of(cliente));
		given(this.userService.getLoggedUser()).willReturn(user);
		
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
	void testListadoContratoServicioPorCliente() throws Exception{
		mockMvc.perform(get("/contratosServicios/misContratos"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("contratosServicios"))
		.andExpect(view().name("contratosServicios/listadoContratosServicios"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/contratosServicios/save")
						.with(csrf())
						.param("fechainicial", "2021/11/17")
						.param("fechafinal", "2030/12/10")
						.param("fechapago", "2025/12/15")
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
						.param("fechainicial", "2017/11/01")
						.param("fechafinal", "")
						.param("fechapago", "2017/12/10")
						.param("periodoPrueba", "true")
						.param("presupuesto", "1"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(model().attributeHasErrors("contratoServicio"))
			.andExpect(model().attributeHasFieldErrors("contratoServicio", "fechafinal"))
			.andExpect(view().name("contratosServicios/editContratoServicio"));
	}
}
