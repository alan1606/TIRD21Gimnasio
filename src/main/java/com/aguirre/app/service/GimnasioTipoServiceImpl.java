package com.aguirre.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aguirre.app.models.entity.GimnasioTipo;
import com.aguirre.app.models.repository.GimnasioTipoRepository;

@Service
public class GimnasioTipoServiceImpl 
implements GimnasioTipoService
{

	@Autowired
	private GimnasioTipoRepository repository;
	
	@Override
	public GimnasioTipo crear(GimnasioTipo gimnasioTipo) {
		return repository.save(gimnasioTipo);
	}

	@Override
	public GimnasioTipo modificar(GimnasioTipo gimnasioTipo) {
		return repository.save(gimnasioTipo);
	}

	@Override
	public Optional<GimnasioTipo> buscarPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public void eliminarPorId(Long id) {
		repository.deleteById(id);
	}

}
