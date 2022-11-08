package com.raspberry.awards.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raspberry.awards.entities.Studio;

public interface StudioRepository extends JpaRepository<Studio, Long>{

	Optional<Studio> findByName(String name);
}
