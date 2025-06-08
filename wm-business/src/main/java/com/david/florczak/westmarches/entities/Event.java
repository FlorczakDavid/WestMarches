package com.david.florczak.westmarches.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_events")
public class Event extends AbstractEntity {

	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@ManyToOne
	@JoinColumn(name="poi_id")
	private PointOfInterest poi;
	
	public Event() {
		// for the ORM
	}

	public Event(String name, String description, PointOfInterest poi) {
		this.name = name;
		this.description = description;
		this.poi = poi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PointOfInterest getPoi() {
		return poi;
	}

	public void setPoi(PointOfInterest poi) {
		this.poi = poi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(name, poi);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(name, other.name) && Objects.equals(poi, other.poi);
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", description=" + description + ", poi=" + poi + "]";
	}
	
	
}
