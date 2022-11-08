package com.raspberry.awards.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raspberry.awards.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

	Optional<Movie>	 findByTitle(String title);
}
