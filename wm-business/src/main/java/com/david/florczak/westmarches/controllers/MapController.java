package com.david.florczak.westmarches.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.david.florczak.westmarches.services.MapService;

@RestController
@RequestMapping("/map")
public class MapController {

	//injection de dependences
	private final MapService service;
	
	public MapController(MapService service) {
		this.service = service;
	}
	
	@GetMapping("/user")
	Object getAccount(@RequestParam String email) {
		return service.getUserMaps(email);
	}
}

