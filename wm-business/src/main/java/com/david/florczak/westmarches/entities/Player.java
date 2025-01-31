package com.david.florczak.westmarches.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_players")
public class Player extends AbstractEntity {
	
	@Column(name = "email")
    @OneToOne(mappedBy = "player")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "username")
	private String username;
	
	public Player() {
		// for the ORM
	}
	
	public Player(String email, String password, String username) {
		this.email = email;
		this.password = password;
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(email);
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
		Player other = (Player) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "Player [email=" + email + ", password=[PROTECTED], username=" + username + "]";
	}
	
	
}
