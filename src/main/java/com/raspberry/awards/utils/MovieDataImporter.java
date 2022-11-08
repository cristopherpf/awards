package com.raspberry.awards.utils;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBeanBuilder;
import com.raspberry.awards.entities.Movie;
import com.raspberry.awards.entities.Producer;
import com.raspberry.awards.entities.Studio;
import com.raspberry.awards.repositories.MovieRepository;
import com.raspberry.awards.repositories.ProducerRepository;
import com.raspberry.awards.repositories.StudioRepository;

@Component
public class MovieDataImporter {
	
	private final static String FILE_NAME = "movielist.csv";
	private final static char SEPARATOR = ';';
	
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private ProducerRepository producerRepository;
	@Autowired
	private StudioRepository studioRepository;
	
	public void loadData(){
		try {
			List<MovieBean> movieBeanList = new CsvToBeanBuilder(getFile()).withType(MovieBean.class).withSeparator(SEPARATOR).build().parse();
			
			for (MovieBean movieBean : movieBeanList) {
				Movie movie = MovieFactory.getMovie(movieBean);
				manageProducers(movie, MovieFactory.getProducerSet(movieBean.getProducers()));
				manageStudios(movie, MovieFactory.getStudioSet(movieBean.getStudios()));
				movieRepository.save(movie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void manageProducers(Movie movie, Set<Producer> producers) {
		for (Producer producer : producers) {
			Optional<Producer> newProducer = producerRepository.findByName(producer.getName());
			if(newProducer.isPresent())
				movie.addProducer(newProducer.get());
			else
				movie.addProducer(producerRepository.save(producer));
		}
	}
	
	private void manageStudios(Movie movie, Set<Studio> studios) {
		for (Studio studio : studios) {
			Optional<Studio> newStudio = studioRepository.findByName(studio.getName());
			if(newStudio.isPresent())
				movie.addStudio(newStudio.get());
			else
				movie.addStudio(studioRepository.save(studio));
		}
	}
	
	private Reader getFile() throws Exception {
		Reader reader = Files.newBufferedReader(Paths.get(getClass().getClassLoader().getResource(FILE_NAME).toURI()));
		return reader;
	}
}
