package com.aguirre.app.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aguirre.app.models.entity.Gimnasio;

@Repository
public interface GimnasioRepository extends CrudRepository<Gimnasio, Long> {
	
}