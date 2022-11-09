package com.raspberry.awards.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raspberry.awards.entities.Studio;
import com.raspberry.awards.service.StudioService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class StudioControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private StudioService service;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private Studio createStudio() {
		return new Studio("Studio Tst");
	}
	
	@Test
	public void shouldCreateNewStudio() throws Exception {
		Studio studio = createStudio();
		
		String json = mapper.writeValueAsString(studio);
		
		when(service.insert(any(Studio.class))).thenReturn(studio);
		
		mvc.perform(
		    MockMvcRequestBuilders.post("/studios")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json)
        ).andExpect(status().isCreated());
		
		ArgumentCaptor<Studio> captor = ArgumentCaptor.forClass(Studio.class);
		verify(service).insert(captor.capture());
		
		assertEquals(studio.getName(), captor.getValue().getName());
	}
	
	@Test
	public void shouldFindStudioById() throws Exception {
		Long id = 1L;
		Studio studio = createStudio();
		studio.setId(id);
		
		when(service.findById(eq(id))).thenReturn(Optional.of(studio));
		
		mvc.perform(
		    MockMvcRequestBuilders.get("/studios/" + id)
			.accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
		 .andExpect(jsonPath("$.name", Matchers.is(studio.getName())));
	}
	
	@Test
	public void shouldFindAllProducers() throws Exception {
		List<Studio> studios = new ArrayList<>();
		studios.add(createStudio());
		
		when(service.findAll()).thenReturn(studios);
		
		mvc.perform(
		    MockMvcRequestBuilders.get("/studios")
			.accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
		.andExpect(jsonPath("$.[0].name", Matchers.is(studios.get(0).getName())));
	}
	
}
