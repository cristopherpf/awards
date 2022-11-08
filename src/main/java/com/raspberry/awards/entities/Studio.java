package com.raspberry.awards.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "studio")
public class Studio {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(nullable = false, unique = true)
	private String name;
    @ManyToMany(mappedBy = "studios", fetch = FetchType.LAZY)
    @JsonIgnore
    @Schema(hidden = true)
	private Set<Movie> movies;
	
	public Studio() {}
	
	public Studio(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Studio(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovie(Set<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Studio [id=" + id + ", name=" + name + "]";
	}
}
