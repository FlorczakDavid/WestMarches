package com.david.florczak.westmarches.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
	
	@GetMapping("/ping")
	public ResponseEntity<Boolean> ping() {
		return ResponseEntity.ok(true);
	}
}
