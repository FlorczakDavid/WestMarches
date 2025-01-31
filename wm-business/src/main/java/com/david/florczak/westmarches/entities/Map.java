package com.david.florczak.westmarches.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_maps")
public class Map extends AbstractEntity {
	
	@Column(name = "name")
	@OneToOne(mappedBy = "map")
	private String name;
	@Column(name = "width")
	private int width;
	@Column(name = "height")
	private int height;
	@OneToOne(optional = false)
    @JoinColumn(name = "email")
    @MapsId
	private Player player;

	public Map() {
		// for the ORM
	}
	
	public Map(String name, int width, int height, Player player) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.player = player;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(name, player);
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
		Map other = (Map) obj;
		return Objects.equals(name, other.name) && Objects.equals(player, other.player);
	}

	@Override
	public String toString() {
		return "Map [name=" + name + ", width=" + width + ", height=" + height + ", player=" + player + "]";
	}
	
	
}
