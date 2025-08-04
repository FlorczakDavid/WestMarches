package com.david.florczak.westmarches.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.david.florczak.westmarches.dtos.DetailedTile;
import com.david.florczak.westmarches.dtos.TileEditDetails;
import com.david.florczak.westmarches.dtos.TileIdentifier;
import com.david.florczak.westmarches.dtos.TileRequest;
import com.david.florczak.westmarches.services.TileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tile")
public class TileController {

	//injection de dependences
	private final TileService service;
	
	public TileController(TileService service) {
		this.service = service;
	}
	
	@GetMapping("/map")
	Object getUserTiles(@RequestParam String email, @RequestParam String map) {
		TileRequest input = new TileRequest(email, map);
		return service.getUserTiles(input);
	}
	
	@GetMapping("/")
	Object getTileDeepDetails(
			@RequestParam String email, 
			@RequestParam String map, 
			@RequestParam int x, 
			@RequestParam int y) {
		TileIdentifier input = new TileIdentifier(email, map, x ,y);
		return service.getTileDeepDetails(input);
	}
	
	@PatchMapping("/")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void editTile(@Valid @RequestBody TileEditDetails input) {
		System.out.println(input);
		service.updateTileDeepDetails(input);
	}
}

