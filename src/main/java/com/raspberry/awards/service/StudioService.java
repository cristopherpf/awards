package com.raspberry.awards.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raspberry.awards.entities.Studio;
import com.raspberry.awards.repositories.StudioRepository;

@Service
public class StudioService {

	@Autowired
	private StudioRepository repository;
	
	public List<Studio> findAll() {
		return repository.findAll();
	}
	
	public Optional<Studio> findById(Long id) {
		return repository.findById(id);
	}
	
	public Optional<Studio> findByName(String name) {
		return repository.findByName(name);
	}

	public Studio insert(Studio studio) {
		return repository.save(studio);
	}

	public void delete(Studio studio) {
		repository.delete(studio);
	}
}
