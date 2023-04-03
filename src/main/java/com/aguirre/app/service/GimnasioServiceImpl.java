package com.aguirre.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aguirre.app.models.entity.Gimnasio;
import com.aguirre.app.models.repository.GimnasioRepository;
 

@Service
public class GimnasioServiceImpl 
implements GimnasioService{

	@Autowired
	private GimnasioRepository repository;
	
	@Override
	public Gimnasio crear(Gimnasio gimnasio) {		
		return repository.save(gimnasio);
	}


	@Override
	public Gimnasio actualizar(Gimnasio gimnasio) {
		return repository.save(gimnasio);
	}

	@Override
	public void eliminarPorId(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Gimnasio> buscarPorId(Long id) {
		return repository.findById(id);
	}


	@Override
	public List<Gimnasio> obtenerTodo() {
		return (List<Gimnasio>) repository.findAll();
	}
	

}
