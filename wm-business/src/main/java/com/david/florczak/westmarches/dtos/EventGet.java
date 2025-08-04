package com.david.florczak.westmarches.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EventGet(
		@NotBlank @Size(max = 100) String name, 
		String description) {

}
