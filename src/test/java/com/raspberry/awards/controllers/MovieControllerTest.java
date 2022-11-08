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
import com.raspberry.awards.entities.Movie;
import com.raspberry.awards.entities.Producer;
import com.raspberry.awards.entities.Studio;
import com.raspberry.awards.service.MovieService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class MovieControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private MovieService service;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private Movie createMovie() {
		Movie movie = new Movie();
		movie.setTitle("Movie 2022");
		movie.setWinner(true);
		movie.setMovieYear(2022);
		movie.addProducer(new Producer("Producer"));
		movie.addStudio(new Studio("Studio"));
		
		return movie;
	}
	
	@Test
	public void shouldCreateNewMovie() throws Exception {
		Movie movie = createMovie();
		
		String json = mapper.writeValueAsString(movie);
		
		when(service.insert(any(Movie.class))).thenReturn(movie);
		
		mvc.perform(
		    MockMvcRequestBuilders.post("/movies")
			.contentType(MediaType.APPLICATION_JSON)
            .content(json)
        ).andExpect(status().isCreated());
		
		ArgumentCaptor<Movie> captor = ArgumentCaptor.forClass(Movie.class);
		verify(service).insert(captor.capture());
		
		assertEquals(movie.getTitle(), captor.getValue().getTitle());
		assertEquals(movie.getMovieYear(), captor.getValue().getMovieYear());
		assertEquals(movie.getWinner(), captor.getValue().getWinner());
		assertEquals(movie.getProducers(), captor.getValue().getProducers());
		assertEquals(movie.getStudios(), captor.getValue().getStudios());
	}
	
	@Test
	public void shouldFindMovieById() throws Exception {
		Long id = 1L;
		Movie movie = createMovie();
		movie.setId(id);
		
		when(service.findById(eq(id))).thenReturn(Optional.of(movie));
		
		mvc.perform(
		    MockMvcRequestBuilders.get("/movies/" + id)
			.accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
		 .andExpect(jsonPath("$.title", Matchers.is(movie.getTitle())))
		 .andExpect(jsonPath("$.movieYear", Matchers.is(movie.getMovieYear())))
		 .andExpect(jsonPath("$.winner", Matchers.is(movie.getWinner())));
	}
	
}
