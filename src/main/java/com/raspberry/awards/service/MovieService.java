package com.raspberry.awards.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raspberry.awards.entities.Movie;
import com.raspberry.awards.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	public List<Movie> findAll() {
		return repository.findAll();
	}
	
	public Optional<Movie> findById(Long id) {
		return repository.findById(id);
	}
	
	public Optional<Movie> findByTitle(String title) {
		return repository.findByTitle(title);
	}

	public Movie insert(Movie movie) {
		return repository.save(movie);
	}

	public void delete(Movie movie) {
		repository.delete(movie);
	}
}
