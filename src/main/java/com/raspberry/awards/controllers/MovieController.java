package com.raspberry.awards.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raspberry.awards.entities.Movie;
import com.raspberry.awards.service.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService service;
	
	@GetMapping
	public ResponseEntity<List<Movie>> findAll() {
		return new ResponseEntity<List<Movie>>(service.findAll(), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Movie> findById(@PathVariable Long id) {
		Optional<Movie> movie = service.findById(id);
		if(movie.isPresent())
			return new ResponseEntity<Movie>(movie.get(), HttpStatus.OK);
		else
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/name")
	public ResponseEntity<Movie> findByTitle(@RequestParam String title) {
		Optional<Movie> movie = service.findByTitle(title);
		if(movie.isPresent())
			return new ResponseEntity<Movie>(movie.get(), HttpStatus.OK);
		else
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Movie> insert(@RequestBody Movie movie) {
		return new ResponseEntity<Movie>(service.insert(movie), HttpStatus.CREATED);
	}
}
