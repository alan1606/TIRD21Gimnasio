package com.aguirre.app.service;

import java.util.List;
import java.util.Optional;

import com.aguirre.app.models.entity.GimnasioTipo;

public interface GimnasioTipoService {

	GimnasioTipo crear(GimnasioTipo gimnasioTipo);
	
	GimnasioTipo modificar(GimnasioTipo gimnasioTipo);
	
	Optional<GimnasioTipo> buscarPorId(Long id);
	
	void eliminarPorId(Long id);


	List<GimnasioTipo> buscarGimnasioTipoPorGimnasioId(Long gimnasioId);

	List<GimnasioTipo>  buscarGimnasiosTiposPorGimnasioIdYTipoId(Long gimnasioId, Long tipoId);

}
