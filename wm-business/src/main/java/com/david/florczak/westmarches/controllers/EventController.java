package com.david.florczak.westmarches.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.david.florczak.westmarches.dtos.EventRequest;
import com.david.florczak.westmarches.services.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

	//injection de dependences
	private final EventService service;
	
	public EventController(EventService service) {
		this.service = service;
	}

//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	void create(@Valid @RequestBody UserCreate inputs) {
//		service.create(inputs);
//	}
	
	@GetMapping("/poi")
	Object getAccount(
			@RequestParam String email, 
			@RequestParam String map,  
			@RequestParam int x,  
			@RequestParam int y, 
			@RequestParam String poi) {
		EventRequest input = new EventRequest(email, map, x, y, poi);
		return service.getPoIEvent(input);
	}
}

