package com.david.florczak.westmarches.dtos;

import java.util.ArrayList;
import java.util.HashMap;

public record TileDeepDetailsNested(
		String description,
		ArrayList<HashMap<String, Object>> pointsOfInterest) {
}
