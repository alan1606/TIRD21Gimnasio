package com.aguirre.app.service;

import java.util.List;
import java.util.Optional;

import com.aguirre.app.models.entity.Gimnasio;

public interface GimnasioService {

	Gimnasio crear(Gimnasio gimnasio);
	
	Optional<Gimnasio> buscarPorId(Long id);
	
	Gimnasio actualizar(Gimnasio gimnasio);
	
	void eliminarPorId(Long id);
	
	List<Gimnasio> obtenerTodo();
}
