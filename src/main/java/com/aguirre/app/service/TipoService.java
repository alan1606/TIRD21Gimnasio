package com.aguirre.app.service;

import java.util.Optional;

import com.aguirre.app.models.entity.Tipo;

public interface TipoService {

	Tipo crear(Tipo tipo);
	Tipo actualizar(Tipo tipo);
	void eliminarPorId(Long id);
	Optional<Tipo> buscarPorId(Long id);
}
