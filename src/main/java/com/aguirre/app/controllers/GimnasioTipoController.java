package com.aguirre.app.controllers;

import com.aguirre.app.models.entity.Gimnasio;
import com.aguirre.app.models.entity.GimnasioTipo;
import com.aguirre.app.models.entity.Tipo;
import com.aguirre.app.service.GimnasioService;
import com.aguirre.app.service.GimnasioTipoService;
import com.aguirre.app.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gimnasios/{id}/tipos-membresias")
public class GimnasioTipoController {

    @Autowired
    private GimnasioTipoService service;

    @Autowired
    private GimnasioService gimnasioService;

    @Autowired
    private TipoService tipoService;

    @GetMapping()
    public ResponseEntity<?> obtenerTiposDeMembresiasPorGimnasio(@PathVariable(name = "id") Long id){
        Optional<Gimnasio> gimnasio = gimnasioService.buscarPorId(id);

        if(gimnasio.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<Tipo> listaBuena = service.buscarGimnasioTipoPorGimnasioId(id).stream()
                .filter(gt -> gt.getActivo())
                .map(gt -> gt.getTipo())
                .collect(Collectors.toList());


        return ResponseEntity.ok(listaBuena);
    }

    @PostMapping()
    public ResponseEntity<?> registrarGimnasioTipo(@PathVariable(name = "id") Long id, @RequestBody Tipo tipoMembresia){
        Optional<Gimnasio> g = gimnasioService.buscarPorId(id);

        if(g.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Optional<Tipo> t = tipoService.buscarPorId(tipoMembresia.getId());

        if(t.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        tipoMembresia = t.get();
        Gimnasio gimnasio =  g.get();

        List<GimnasioTipo> encontrados = service.buscarGimnasiosTiposPorGimnasioIdYTipoId(gimnasio.getId(), tipoMembresia.getId());

        if(encontrados.isEmpty()){
           GimnasioTipo temporal = new GimnasioTipo();
           temporal.setGimnasio(gimnasio);
           temporal.setTipo(tipoMembresia);
           temporal.setActivo(true);
           return ResponseEntity.ok(service.crear(temporal));
        }

        GimnasioTipo elemento =  encontrados.get(0);
        elemento.setActivo(true);

        return  ResponseEntity.ok().body(service.modificar(elemento));

    }

    @DeleteMapping("/{idMembresia}")
    public ResponseEntity<?> eliminarTipoDeMembresiaEnGimnasio(@PathVariable(name = "id") Long idGimnasio, @PathVariable Long idMembresia){
        List<GimnasioTipo> encontrados = service.buscarGimnasiosTiposPorGimnasioIdYTipoId(idGimnasio, idMembresia);

        if(encontrados.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        for(GimnasioTipo temporal : encontrados){
            temporal.setActivo(false);
            service.modificar(temporal);
        }

        return ResponseEntity.noContent().build();
    }
}
