package com.david.florczak.westmarches.dtos;

import java.util.List;

import com.david.florczak.westmarches.validators.UniqueByProperty;

import jakarta.validation.Valid;

public record DetailedTile(
		String description, 
		@Valid @UniqueByProperty(fieldName = "name") List<DetailedPointOfInterest> pointsOfInterest) {
	
	public DetailedTile {
		if(description == null) {
			description = "";
		}
	}
}
