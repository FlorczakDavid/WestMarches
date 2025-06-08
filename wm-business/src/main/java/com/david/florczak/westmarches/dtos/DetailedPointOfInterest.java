package com.david.florczak.westmarches.dtos;

import java.util.List;

public record DetailedPointOfInterest(String name, String description, List<EventGet> events) {

}
