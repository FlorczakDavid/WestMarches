package com.david.florczak.westmarches.entities;


import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_tiles")
public class Tile extends AbstractEntity {

	@Column(name = "x")
	private int x;
	@Column(name = "y")
	private int y;
	@Column(name = "terrain")
	private String terrain;
	@Column(name = "description")
	private String description;
	@ManyToOne
	@JoinColumn(name="map_id")
	private Map map;
	
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Tile() {
		// for the ORM
	}

	public Tile(int x, int y, String terrain, String description, Map map) {
		this.x = x;
		this.y = y;
		this.terrain = terrain;
		this.description = description;
		this.map = map;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(x, y, map);
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
		return Objects.equals(x, other.x) && Objects.equals(y, other.y) && Objects.equals(map, other.map);
	}

	@Override
	public String toString() {
		return "Tile [x=" + x + "y=" + y + ", terrain=" + terrain + ", description=" + description + ", map="
				+ map + "]";
	}
	
	
}
