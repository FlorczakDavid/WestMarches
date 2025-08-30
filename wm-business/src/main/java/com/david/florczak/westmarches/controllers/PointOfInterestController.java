package com.david.florczak.westmarches.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.david.florczak.westmarches.dtos.PointOfInterestRequest;
import com.david.florczak.westmarches.services.PointOfInterestService;

@RestController
@RequestMapping("/poi")
public class PointOfInterestController {

	//injection de dependences
	private final PointOfInterestService service;
	
	public PointOfInterestController(PointOfInterestService service) {
		this.service = service;
	}
	
	@GetMapping("/tile")
	Object getAccount(@RequestParam String email, @RequestParam String map,  @RequestParam int x,  @RequestParam int y) {
		PointOfInterestRequest input = new PointOfInterestRequest(email, map, x, y);
		Object ret = service.getTilePoIs(input);
		return ret;
	}
}

