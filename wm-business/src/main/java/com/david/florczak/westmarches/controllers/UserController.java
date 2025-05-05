package com.david.florczak.westmarches.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.david.florczak.westmarches.dtos.UserCreate;
import com.david.florczak.westmarches.dtos.UserLogin;
import com.david.florczak.westmarches.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	//injection de dependences
	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	void create(@Valid @RequestBody UserCreate inputs) {
		service.create(inputs);
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	Object login(@RequestBody UserLogin inputs) {
		return service.login(inputs);
	}
	
	@GetMapping("/with-role")
	Object withRole() {
		return "with role";
	}
	
	@GetMapping("/user")
	Object getAccount(@RequestBody UserLogin inputs) {
		return service.getUser(inputs.email());
	}
}
