package com.david.florczak.westmarches.dtos;

import jakarta.validation.Valid;

public record TileEditDetails(String email, String map, int x, int y, @Valid DetailedTile details) {
			
}