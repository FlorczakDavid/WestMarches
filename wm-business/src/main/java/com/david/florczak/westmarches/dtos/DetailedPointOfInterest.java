package com.david.florczak.westmarches.dtos;

import java.util.List;

import com.david.florczak.westmarches.validators.UniqueByProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DetailedPointOfInterest(
		@NotBlank @Size(max = 100) String name, 
		String description, 
		@Valid @UniqueByProperty(fieldName = "name") List<EventGet> events) {

}
