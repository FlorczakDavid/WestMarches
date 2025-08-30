package com.david.florczak.westmarches.dtos;

import java.util.List;
import java.util.Objects;

import com.david.florczak.westmarches.validators.UniqueByProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DetailedPointOfInterest(
		@NotBlank @Size(max = 100) String name, 
		String description, 
		@Valid @UniqueByProperty(fieldName = "name") List<EventGet> events) {

	@Override
	public String toString() {
		return "DetailedPointOfInterest [name=" + name + ", description=" + description + ", events=" + events + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, events, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailedPointOfInterest other = (DetailedPointOfInterest) obj;
		return Objects.equals(description, other.description) && Objects.equals(events, other.events)
				&& Objects.equals(name, other.name);
	}

}
