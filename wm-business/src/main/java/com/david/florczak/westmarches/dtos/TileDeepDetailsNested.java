package com.david.florczak.westmarches.dtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public record TileDeepDetailsNested(String description, ArrayList<HashMap<String, Object>> pointsOfInterest) {

	@Override
	public String toString() {
		return "TileDeepDetailsNested [description=" + description + ", pointsOfInterest=" + pointsOfInterest + "]";
	}

}