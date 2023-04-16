package com.aguirre.app.controllers;

import com.aguirre.app.models.entity.Tipo;
import com.aguirre.app.service.TipoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("tipos-membresias")
public class TipoController {

    @Autowired
    private TipoService service;

    @GetMapping
    public ResponseEntity<?> mostrarTodo(){
        return ResponseEntity.ok(service.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Optional<Tipo> tipoOptional = service.buscarPorId(id);

        if(tipoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tipoOptional.get());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Tipo tipo){
        return ResponseEntity.ok(service.crear(tipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Tipo tipo){
        Optional<Tipo> tipoOptional = service.buscarPorId(id);

        if(tipoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Tipo tipoDb = tipoOptional.get();

        tipoDb.setNombre(tipo.getNombre());
        tipoDb.setDuracionDias(tipo.getDuracionDias());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.actualizar(tipoDb));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Tipo> tipoOptional = service.buscarPorId(id);

        if(tipoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.eliminarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
