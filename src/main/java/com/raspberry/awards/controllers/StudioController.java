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

import com.raspberry.awards.entities.Studio;
import com.raspberry.awards.service.StudioService;

@RestController
@RequestMapping(value = "/studios")
public class StudioController {

	@Autowired
	private StudioService service;
	
	@GetMapping
	public ResponseEntity<List<Studio>> findAll() {
		return new ResponseEntity<List<Studio>>(service.findAll(), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Studio> findById(@PathVariable Long id) {
		Optional<Studio> studio = service.findById(id);
		if(studio.isPresent())
			return new ResponseEntity<Studio>(studio.get(), HttpStatus.OK);
		else
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/name")
	public ResponseEntity<Studio> findByName(@RequestParam String name) {
		Optional<Studio> studio = service.findByName(name);
		if(studio.isPresent())
			return new ResponseEntity<Studio>(studio.get(), HttpStatus.OK);
		else
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Studio> insert(@RequestBody Studio studio) {
		return new ResponseEntity<Studio>(service.insert(studio), HttpStatus.CREATED);
	}
}
