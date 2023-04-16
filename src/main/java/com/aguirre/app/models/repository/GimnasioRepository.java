package com.aguirre.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aguirre.app.models.entity.Gimnasio;

import java.util.List;

@Repository
public interface GimnasioRepository extends CrudRepository<Gimnasio, Long> {

    @Query("select g from Gimnasio g where upper(g.nombre) like concat(upper(?1), '%')")
    List<Gimnasio> encontrarCoincidenciaPorNombre(String nombre);
}
