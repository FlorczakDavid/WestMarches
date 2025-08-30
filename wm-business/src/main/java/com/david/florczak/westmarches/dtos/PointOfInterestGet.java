package com.david.florczak.westmarches.dtos;

import java.util.Objects;

public record PointOfInterestGet(String name, String description) {

	@Override
	public String toString() {
		return "PointOfInterestGet [name=" + name + ", description=" + description + "]";
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
		PointOfInterestGet other = (PointOfInterestGet) obj;
		return Objects.equals(name, other.name);
	}

}
