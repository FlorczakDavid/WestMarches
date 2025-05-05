package com.david.florczak.westmarches.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_maps")
public class Map extends AbstractEntity {
	
	@Column(name = "name")
	private String name;
	@Column(name = "width")
	private int width;
	@Column(name = "height")
	private int height;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Map() {
		// for the ORM
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

//	public User getUser() {
//		return user;
//	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map(String name, int width, int height, User user) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(name, user);
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
		return Objects.equals(name, other.name) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Map [name=" + name + ", width=" + width + ", height=" + height + ", player=" + user + "]";
	}
	
	
}
