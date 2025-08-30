package com.david.florczak.westmarches.dtos;

import java.util.Objects;

import com.david.florczak.westmarches.enums.ChangeType;

public record TileChange(ChangeType type, String poiName, String poiDescription, String eventName, String eventDescription) {

	@Override
	public String toString() {
		return "TileChange [type=" + type + ", poiName=" + poiName + ", poiDescription=" + poiDescription
				+ ", eventName=" + eventName + ", eventDescription=" + eventDescription + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventName, poiName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TileChange other = (TileChange) obj;
		return Objects.equals(eventName, other.eventName) && Objects.equals(poiName, other.poiName);
	}
	
}

