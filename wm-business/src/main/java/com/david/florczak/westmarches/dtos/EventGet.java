package com.david.florczak.westmarches.dtos;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EventGet(
		@NotBlank @Size(max = 100) String name, 
		String description) {

	@Override
	public String toString() {
		return "EventGet [name=" + name + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventGet other = (EventGet) obj;
		return Objects.equals(name, other.name);
	}

}
