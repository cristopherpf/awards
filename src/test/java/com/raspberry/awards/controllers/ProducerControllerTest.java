package com.raspberry.awards.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.raspberry.awards.entities.Producer;
import com.raspberry.awards.service.ProducerService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class ProducerControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ProducerService service;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private Producer createProducer() {
		return new Producer("Producer Tst");
	}
	
	@Test
	public void shouldCreateNewProducer() throws Exception {
		Producer producer = createProducer();
		
		String json = mapper.writeValueAsString(producer);
		
		when(service.insert(any(Producer.class))).thenReturn(producer);
		
		mvc.perform(
		    MockMvcRequestBuilders.post("/producers")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json)
        ).andExpect(status().isCreated());
		
		ArgumentCaptor<Producer> captor = ArgumentCaptor.forClass(Producer.class);
		verify(service).insert(captor.capture());
		
		assertEquals(producer.getName(), captor.getValue().getName());
	}
	
	@Test
	public void shouldFindProducerById() throws Exception {
		Long id = 1L;
		Producer producer = createProducer();
		producer.setId(id);
		
		when(service.findById(eq(id))).thenReturn(Optional.of(producer));
		
		mvc.perform(
		    MockMvcRequestBuilders.get("/producers/" + id)
			.accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
		 .andExpect(jsonPath("$.name", Matchers.is(producer.getName())));
	}
	
}
