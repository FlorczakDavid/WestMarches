package com.david.florczak.westmarches.dtos;

import com.david.florczak.westmarches.enums.ChangeType;

public record TileChange(
	    ChangeType type,
	    String poiName,
	    String poiDescription,
	    String eventName,
	    String eventDescription
	) {}

