package com.raspberry.awards.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String title;
	@Column(nullable = false)
	private Integer movieYear;
	@Column(nullable = false)
	private Boolean winner;
	@Schema(hidden = true)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "movie_studio",
			   joinColumns = @JoinColumn(name = "movie_id"),
			   inverseJoinColumns = @JoinColumn(name = "studio_id"))
	private Set<Studio> studios = new HashSet<Studio>();
	@Schema(hidden = true)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "movie_producer",
			   joinColumns = @JoinColumn(name = "movie_id"),
			   inverseJoinColumns = @JoinColumn(name = "producer_id"))
	private Set<Producer> producers = new HashSet<Producer>();
	
	public Movie() {}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getMovieYear() {
		return movieYear;
	}

	public void setMovieYear(Integer movieYear) {
		this.movieYear = movieYear;
	}
	
	public Boolean getWinner() {
		return winner;
	}

	public void setWinner(Boolean winner) {
		this.winner = winner;
	}

	public Set<Studio> getStudios() {
		return studios;
	}

	public void setStudios(Set<Studio> studio) {
		this.studios = studio;
	}
	
	public void addStudio(Studio studio) {
		this.studios.add(studio);
	}

	public Set<Producer> getProducers() {
		return producers;
	}

	public void setProducers(Set<Producer> producers) {
		this.producers = producers;
	}
	
	public void addProducer(Producer producer) {
		this.producers.add(producer);
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", movieYear=" + movieYear + ", studios=" + studios + ", producers="
				+ producers + ", winner=" + winner + "]";
	}
}
