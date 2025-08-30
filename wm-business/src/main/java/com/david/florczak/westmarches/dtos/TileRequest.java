package com.david.florczak.westmarches.dtos;

import java.util.Objects;

public record TileRequest(String email, String map) {

	@Override
	public String toString() {
		return "TileRequest [email=" + email + ", map=" + map + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, map);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TileRequest other = (TileRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(map, other.map);
	}

	
}
