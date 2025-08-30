package com.david.florczak.westmarches.dtos;

import java.util.Objects;

public record EventRequest(String email, String map, int x, int y, String poi) {

	@Override
	public String toString() {
		return "EventRequest [email=" + email + ", map=" + map + ", x=" + x + ", y=" + y + ", poi=" + poi + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, map, poi, x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventRequest other = (EventRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(map, other.map) && Objects.equals(poi, other.poi)
				&& x == other.x && y == other.y;
	}
	
}
