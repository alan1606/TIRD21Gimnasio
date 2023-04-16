package com.aguirre.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.aguirre.app.models.entity.Membresia;

public interface MembresiaService {

	Membresia crear(Membresia membresia);
	Membresia modificar(Membresia membresia);
	Optional<Membresia> buscarPorId(Long id);
	void eliminarPorId(Long id);

	List<Membresia> obtenerTodo();

	List<Membresia> encontrarMembresiasActivasEnFecha(LocalDate fecha);


	List<Membresia> encontrarMembresiasActivasEnFechaYPersona(LocalDate fecha, Long personaId);
}
