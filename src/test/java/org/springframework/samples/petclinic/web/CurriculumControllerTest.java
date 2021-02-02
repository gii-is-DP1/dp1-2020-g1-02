package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.samples.petclinic.model.Curriculum;
import org.springframework.samples.petclinic.model.TipoCategoria;
import org.springframework.samples.petclinic.service.CurriculumService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers= CurriculumController.class,
			includeFilters= @ComponentScan.Filter(value = Curriculum.class, type = FilterType.ASSIGNABLE_TYPE ),
			excludeFilters= @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
			excludeAutoConfiguration= SecurityConfiguration.class)

public class CurriculumControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CurriculumService curriculumService;
	
	private Curriculum curriculum;
	
	@BeforeEach
	void setup() {
		curriculum = new Curriculum();
		curriculum.setId(1);
		curriculum.setNombre("Jose Carlos");
		curriculum.setTipocategoria(TipoCategoria.Lavanderia);
		
		given(this.curriculumService.findCurriculumById(1)).willReturn(Optional.of(curriculum));
		
		List<Curriculum> curriculums = new ArrayList<Curriculum>();
		curriculums.add(curriculum);
		given(this.curriculumService.findAll()).willReturn(curriculums);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testListadoCurriculums() throws Exception{
		mockMvc.perform(get("/curriculums")).andExpect(status().isOk())
		.andExpect(model().attributeExists("curriculum"))
		.andExpect(view().name("curriculums/listadoCurriculums"));
	}
}