package com.raspberry.awards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.raspberry.awards.utils.MovieDataImporter;

@SpringBootApplication
public class AwardsApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(AwardsApplication.class, args);
		MovieDataImporter movieDataImporter = applicationContext.getBean(MovieDataImporter.class);
		movieDataImporter.loadData();
	}
}