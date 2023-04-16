package com.aguirre.app.models.repository;

import com.aguirre.app.models.entity.Gimnasio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aguirre.app.models.entity.GimnasioTipo;

import java.util.List;
import java.util.Optional;

@Repository
public interface GimnasioTipoRepository
extends CrudRepository<GimnasioTipo, Long>
{

    @Query("select gt from GimnasioTipo gt where gt.gimnasio.id = ?1")
    List<GimnasioTipo> encontrarPorGimnasioId(Long gimnasioId);

    @Query("select gt from GimnasioTipo gt where gt.gimnasio.id = ?1 and gt.tipo.id = ?2")
    List<GimnasioTipo> buscarGimnasiosTiposPorGimnasioIdYTipoId(Long gimnasioId, Long tipoId);

}
