package com.aguirre.app.service;

import java.util.Optional;

import com.aguirre.app.models.entity.Persona;

public interface PersonaService 
{
	Persona crear(Persona persona);
	Persona actualizar(Persona persona);
	void eliminarPorId(Long id);
	Optional<Persona> buscarPorId(Long id);	
}
