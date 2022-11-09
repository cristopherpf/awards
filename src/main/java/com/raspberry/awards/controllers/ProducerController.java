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

import com.raspberry.awards.entities.BestWorstProducers;
import com.raspberry.awards.entities.Producer;
import com.raspberry.awards.service.ProducerService;

@RestController
@RequestMapping(value = "/producers")
public class ProducerController {

	@Autowired
	private ProducerService service;
	
	@GetMapping
	public ResponseEntity<List<Producer>> findAll() {
		return new ResponseEntity<List<Producer>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Producer> findById(@PathVariable Long id) {
		Optional<Producer> studio = service.findById(id);
		if(studio.isPresent())
			return new ResponseEntity<Producer>(studio.get(), HttpStatus.OK);
		else
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/name")
	public ResponseEntity<Producer> findByName(@RequestParam String name) {
		Optional<Producer> producer = service.findByName(name);
		if(producer.isPresent())
			return new ResponseEntity<Producer>(producer.get(), HttpStatus.OK);
		else
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/bestandworst")
	public ResponseEntity<BestWorstProducers> getBestAndWorst() {
		return new ResponseEntity<BestWorstProducers>(service.getBestWorstProducers(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Producer> insert(@RequestBody Producer producer) {
		return new ResponseEntity<Producer>(service.insert(producer), HttpStatus.CREATED);
	}
}
