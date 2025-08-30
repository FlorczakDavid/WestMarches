package com.david.florczak.westmarches.dtos;

import java.util.List;
import java.util.Objects;

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

	@Override
	public String toString() {
		return "DetailedTile [description=" + description + ", pointsOfInterest=" + pointsOfInterest + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, pointsOfInterest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailedTile other = (DetailedTile) obj;
		return Objects.equals(description, other.description)
				&& Objects.equals(pointsOfInterest, other.pointsOfInterest);
	}
	
	
}
