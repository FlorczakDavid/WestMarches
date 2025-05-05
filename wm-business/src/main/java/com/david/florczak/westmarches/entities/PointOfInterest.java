package com.david.florczak.westmarches.entities;

import java.awt.Point;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_points_of_interest")
public class PointOfInterest extends AbstractEntity {
	
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@ManyToOne
	@JoinColumn(name="tile_id")
	private Tile tile;
	
	public PointOfInterest() {
		// for the ORM
	}

	public PointOfInterest(String name, String description, Tile tile) {
		this.name = name;
		this.description = description;
		this.tile = tile;
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

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(name, tile);
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
		PointOfInterest other = (PointOfInterest) obj;
		return Objects.equals(name, other.name) && Objects.equals(tile, other.tile);
	}

	@Override
	public String toString() {
		return "PointOfInterest [name=" + name + ", description=" + description + ", tile=" + tile + "]";
	}

}
