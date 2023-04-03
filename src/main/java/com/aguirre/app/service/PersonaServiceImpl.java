package com.aguirre.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aguirre.app.models.entity.Persona;
import com.aguirre.app.models.repository.PersonaRepository;

@Service
public class PersonaServiceImpl 
implements PersonaService{

	@Autowired
	private PersonaRepository repository;
	
	@Override
	public Persona crear(Persona persona) {
		return repository.save(persona);
	}

	@Override
	public Persona actualizar(Persona persona) {
		return repository.save(persona);
	}

	@Override
	public void eliminarPorId(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Persona> buscarPorId(Long id) {
		return repository.findById(id);
	}

}
