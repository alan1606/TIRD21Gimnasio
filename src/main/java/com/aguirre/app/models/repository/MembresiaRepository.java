package com.aguirre.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aguirre.app.models.entity.Membresia;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MembresiaRepository 
extends CrudRepository<Membresia, Long>
{


    // 09/04/2023 09/04/2023
    // 09/04/2023
    @Query("select m from Membresia m where ?1 between m.fechaInicio and m.fechaFin")
    List<Membresia> encontrarMembresiasActivasEnFecha(LocalDate fecha);

    @Query("select m from Membresia m where ?1 between m.fechaInicio and m.fechaFin and m.persona.id = ?2")
    List<Membresia> encontrarMembresiasActivasEnFechaYPersona(LocalDate fecha, Long personaId);
}
