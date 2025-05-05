package com.david.florczak.westmarches.dtos;

import java.util.Objects;

public record UserLogin(String email, String password) {

	@Override
	public String toString() {
		//password is protected
		return "UserLogin [email=" + email + ", password=[REDACTED]]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserLogin)) {
			return false;
		}
		UserLogin other = (UserLogin) obj;
		return Objects.equals(email, other.email);
	}

}