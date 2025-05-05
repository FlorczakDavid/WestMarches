package com.david.florczak.westmarches.dtos;

import java.util.Objects;

public record MapGet(String name, int height, int width) {

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
		MapGet other = (MapGet) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "MapGet [name=" + name + ", height=" + height + ", width=" + width + "]";
	}
	
	

}
