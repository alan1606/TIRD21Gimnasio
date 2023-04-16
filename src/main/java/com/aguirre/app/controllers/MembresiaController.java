package com.aguirre.app.controllers;

import com.aguirre.app.models.entity.Membresia;
import com.aguirre.app.models.entity.Tipo;
import com.aguirre.app.service.MembresiaService;
import com.aguirre.app.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/membresias")
public class MembresiaController {

    @Autowired
    private MembresiaService service;

    @Autowired
    private TipoService tipoService;

    @GetMapping
    public ResponseEntity<?> obtenerTodo(){
        return ResponseEntity.ok(service.obtenerTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Optional<Membresia> membresiaOptional = service.buscarPorId(id);

        if(membresiaOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(membresiaOptional.get());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Membresia membresia){

        if(membresia.getPersona() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(membresia.getTipo() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(membresia.getTipo().getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(membresia.getFechaInicio() == null){
            membresia.setFechaInicio(LocalDate.now());
        }

        Optional<Tipo> tipo = tipoService.buscarPorId(membresia.getTipo().getId());

        if(tipo.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe ese tipo de membresia");
        }

        membresia.setTipo(tipo.get());

        membresia.setFechaFin(membresia.getFechaInicio().plusDays(membresia.getTipo().getDuracionDias()-1));

        return ResponseEntity.ok(service.crear(membresia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id){
        Optional<Membresia> membresiaOptional = service.buscarPorId(id);

        if(membresiaOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.eliminarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Membresia membresia){
        Optional<Membresia> membresiaOptional = service.buscarPorId(id);

        if(membresiaOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Membresia membresiaDb = membresiaOptional.get();

        membresiaDb.setPersona(membresia.getPersona());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.modificar(membresiaDb));
    }

    @GetMapping("/activas")
    public ResponseEntity<?> encontrarMembresiasActivas(){
        return ResponseEntity.ok(service.encontrarMembresiasActivasEnFecha(LocalDate.now()));
    }

    @GetMapping("/activas/persona/{id}")
    public ResponseEntity<?> encontrarMembresiasActivasDePersona(@PathVariable Long id){
        return ResponseEntity.ok(service.encontrarMembresiasActivasEnFechaYPersona(LocalDate.now(), id));
    }

}
