package com.aguirre.app.controllers;

import com.aguirre.app.models.entity.Persona;
import com.aguirre.app.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService service;

    @GetMapping
    public ResponseEntity<?> obtenerTodo(){
        return ResponseEntity.ok(service.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id){
        Optional<Persona> p = service.buscarPorId(id);

        if(p.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(p.get());
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Persona persona){
        return ResponseEntity.ok(service.crear(persona));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Persona persona){
        Optional<Persona> p = service.buscarPorId(id);

        if(p.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Persona personaDb = p.get();
        personaDb.setApellidos(persona.getApellidos());
        personaDb.setEmail(persona.getEmail());
        personaDb.setSexo(persona.getSexo());
        personaDb.setNombres(persona.getNombres());
        personaDb.setFechaNacimiento(persona.getFechaNacimiento());
        personaDb.setTelefono(persona.getTelefono());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.actualizar(personaDb));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id){
        Optional<Persona> p = service.buscarPorId(id);

        if(p.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.eliminarPorId(id);
        return ResponseEntity.noContent().build();

    }

}
