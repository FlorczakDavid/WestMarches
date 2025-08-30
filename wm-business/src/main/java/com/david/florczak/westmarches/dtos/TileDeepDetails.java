package com.david.florczak.westmarches.dtos;

public record TileDeepDetails(String description, String poiName, String poiDescription, String eventName, String eventDescription) {

	@Override
	public String toString() {
		return "TileDeepDetails [description=" + description + ", poiName=" + poiName + ", poiDescription="
				+ poiDescription + ", eventName=" + eventName + ", eventDescription=" + eventDescription + "]";
	}

}
