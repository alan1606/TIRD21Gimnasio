package com.aguirre.app.service;

import java.util.Optional;

import com.aguirre.app.models.entity.Membresia;

public interface MembresiaService {

	Membresia crear(Membresia membresia);
	Membresia modificar(Membresia membresia);
	Optional<Membresia> buscarPorId(Long id);
	void eliminarPorId(Long id);
	
}
