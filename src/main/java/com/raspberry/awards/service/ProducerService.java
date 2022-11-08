package com.raspberry.awards.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raspberry.awards.entities.BestWorstProducers;
import com.raspberry.awards.entities.Producer;
import com.raspberry.awards.repositories.ProducerRepository;

@Service
public class ProducerService {

	@Autowired
	private ProducerRepository repository;
	
	public List<Producer> findAll() {
		return repository.findAll();
	}
	
	public Optional<Producer> findById(Long id) {
		return repository.findById(id);
	}
	
	public Optional<Producer> findByName(String name) {
		return repository.findByName(name);
	}

	public Producer insert(Producer producer) {
		return repository.save(producer);
	}

	public void delete(Producer producer) {
		repository.delete(producer);
	}
	
	public BestWorstProducers getBestWorstProducers() {
		BestWorstProducers bwp = new BestWorstProducers();
		bwp.setMax(repository.getWorstWinners());
		bwp.setMin(repository.getBestWinners());
		return bwp;
	}
}
