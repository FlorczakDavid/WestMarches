package com.david.florczak.westmarches.entities;

import java.awt.Point;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_tiles")
public class Tile extends AbstractEntity{

	@Column(name = "coordinates")
	@OneToOne(mappedBy = "tile")
	private Point coordinates;
	@Column(name = "terrain")
	private String terrain;
	@Column(name = "description")
	private String description;
	@OneToOne(optional = false)
	@JoinColumn(name = "map_name")
	@MapsId
	private Map map;
	
	public Tile() {
		// for the ORM
	}

	public Tile(Point coordinates, String terrain, String description, Map map) {
		this.coordinates = coordinates;
		this.terrain = terrain;
		this.description = description;
		this.map = map;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(coordinates, map);
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
		Tile other = (Tile) obj;
		return Objects.equals(coordinates, other.coordinates) && Objects.equals(map, other.map);
	}

	@Override
	public String toString() {
		return "Tile [coordinates=" + coordinates + ", terrain=" + terrain + ", description=" + description + ", map="
				+ map + "]";
	}
	
	
}
