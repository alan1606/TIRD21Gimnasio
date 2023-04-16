package com.aguirre.app.controllers;

import com.aguirre.app.models.entity.Gimnasio;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aguirre.app.service.GimnasioService;

import java.util.Optional;

@RestController
@RequestMapping("/gimnasios")
public class GimnasioController {

	@Autowired
	private GimnasioService service;
	
	@GetMapping
	public ResponseEntity<?> obtenerTodo(){
		return ResponseEntity.ok(service.obtenerTodo());
	}

	@GetMapping("/{identificador}")
	public ResponseEntity<?> buscarPorId(@PathVariable(name = "identificador") Long id){
		Optional<Gimnasio> gym = service.buscarPorId(id);
		if(gym.isEmpty()){
			return ResponseEntity.notFound().build();
		}

		Gimnasio gimnasio = gym.get();
		return ResponseEntity.ok(gimnasio);
	}



	@PostMapping
	public ResponseEntity crear(@RequestBody Gimnasio gimnasio){
		return ResponseEntity.ok(service.crear(gimnasio));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> modificar(@PathVariable(name = "id") Long id, @RequestBody Gimnasio gymNuevaInfo){
		Optional<Gimnasio> gym = service.buscarPorId(id);
		if(gym.isEmpty()){
			return ResponseEntity.notFound().build();
		}


		Gimnasio gimnasioDb = gym.get();

		gimnasioDb.setDireccion(gymNuevaInfo.getDireccion());
		gimnasioDb.setNombre(gymNuevaInfo.getNombre());
		gimnasioDb.setTelefono(gymNuevaInfo.getTelefono());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.actualizar(gimnasioDb));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPorId(@PathVariable Long id){
		Optional<Gimnasio> gym = service.buscarPorId(id);
		if(gym.isEmpty()){
			return ResponseEntity.notFound().build();
		}

		service.eliminarPorId(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<?> encontrarPorNombre(@PathVariable String nombre){
		return ResponseEntity.ok(service.encontrarCoincidenciaPorNombre(nombre));
	}
}
