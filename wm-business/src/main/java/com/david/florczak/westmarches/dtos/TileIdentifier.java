package com.david.florczak.westmarches.dtos;

import java.util.Objects;

public record TileIdentifier(String mail, String map, int x , int y) {

	@Override
	public String toString() {
		return "TileIdentifier [mail=" + mail + ", map=" + map + ", x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(mail, map, x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TileIdentifier other = (TileIdentifier) obj;
		return Objects.equals(mail, other.mail) && Objects.equals(map, other.map) && x == other.x && y == other.y;
	}

}