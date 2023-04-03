package com.aguirre.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aguirre.app.service.GimnasioService;

@RestController
@RequestMapping("/gimnasios")
public class GimnasioController {

	@Autowired
	private GimnasioService service;
	
	@GetMapping
	public ResponseEntity<?> obtenerTodo(){
		return ResponseEntity.ok(service.obtenerTodo());
	}
	
}
