package com.david.florczak.westmarches.dtos;

import java.util.Objects;

public record PointOfInterestRequest(String email, String map, int x, int y) {

	@Override
	public String toString() {
		return "PointOfInterestRequest [email=" + email + ", map=" + map + ", x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, map, x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointOfInterestRequest other = (PointOfInterestRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(map, other.map) && x == other.x && y == other.y;
	}

}
