package com.david.florczak.westmarches.dtos;

import java.util.Objects;

public record TileGet(int x, int y, String terrain, String description) {

	@Override
	public String toString() {
		return "TileGet [x=" + x + ", y=" + y + ", terrain=" + terrain + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TileGet other = (TileGet) obj;
		return x == other.x && y == other.y;
	}
	
}