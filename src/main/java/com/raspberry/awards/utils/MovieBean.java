package com.raspberry.awards.utils;

import com.opencsv.bean.CsvBindByName;

public class MovieBean {
	
	@CsvBindByName
	private Integer year;
	@CsvBindByName
	private String title;
	@CsvBindByName
	private String studios;
	@CsvBindByName
	private String producers;
	@CsvBindByName
	private String winner;
	
	public MovieBean() {}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudios() {
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	@Override
	public String toString() {
		return getYear() + " | " + getTitle() + " | " + getStudios() + " | " + getProducers() + " | " + getWinner();
	}
}
