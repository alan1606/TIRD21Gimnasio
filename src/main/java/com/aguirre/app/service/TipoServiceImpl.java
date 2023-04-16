package com.aguirre.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aguirre.app.models.entity.Tipo;
import com.aguirre.app.models.repository.TipoRepository;

@Service
public class TipoServiceImpl 
implements TipoService
{

	@Autowired
	private TipoRepository repository;

	@Override
	public Tipo crear(Tipo tipo) {
		return repository.save(tipo);
	}

	@Override
	public Tipo actualizar(Tipo tipo) {
		return repository.save(tipo);
	}

	@Override
	public void eliminarPorId(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Tipo> buscarPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Tipo> obtenerTodo() {
		return (List<Tipo>) repository.findAll();
	}


}
