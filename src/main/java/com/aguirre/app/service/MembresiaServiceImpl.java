package com.aguirre.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aguirre.app.models.entity.Membresia;
import com.aguirre.app.models.repository.MembresiaRepository;

@Service
public class MembresiaServiceImpl 
implements MembresiaService
{

	@Autowired
	private MembresiaRepository repository;
	
	@Override
	public Membresia crear(Membresia membresia) {
		return repository.save(membresia);
	}

	@Override
	public Membresia modificar(Membresia membresia) {
		return repository.save(membresia);
	}

	@Override
	public Optional<Membresia> buscarPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public void eliminarPorId(Long id) {
		repository.deleteById(id);
	}

}
