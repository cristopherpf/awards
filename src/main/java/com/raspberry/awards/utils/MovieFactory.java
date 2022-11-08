package com.raspberry.awards.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.raspberry.awards.entities.Movie;
import com.raspberry.awards.entities.Producer;
import com.raspberry.awards.entities.Studio;

@Component
public class MovieFactory {
	
	public static Movie getMovie(MovieBean movieBean) {
		Movie movie = new Movie();
		movie.setTitle(movieBean.getTitle());
		movie.setMovieYear(movieBean.getYear());
		movie.setWinner(StringUtil.getBooleanByString(movieBean.getWinner()));		
		return movie;
	}
	
	public static List<Movie> getMovies(List<MovieBean> movieBeanList) {
		List<Movie> movies = new ArrayList<Movie>();
		for (MovieBean movieBean : movieBeanList)
			movies.add(MovieFactory.getMovie(movieBean));
		return movies;
	}
	
	public static Set<Producer> getProducerSet(String value) {
		Set<Producer> list = new HashSet<Producer>();
		String[] splitedValue = StringUtil.split(value, ",");
		for (int i = 0; i < splitedValue.length; i++) {
			list.add(new Producer(splitedValue[i].trim()));
		}
		
		return list;
	}
	
	public static Set<Studio> getStudioSet(String value) {
		Set<Studio> list = new HashSet<Studio>();
		String[] splitedValue = StringUtil.split(value, ",");
		for (int i = 0; i < splitedValue.length; i++) {
			list.add(new Studio(splitedValue[i].trim()));
		}
		
		return list;
	}
}
