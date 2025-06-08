package com.david.florczak.westmarches.dtos;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public record UserLoginInfo(String token, List<String> roles, long exp) {

	@Override
	public String toString() {
		return "UserLoginInfo [token=[REDACTED], " + "roles=" + roles + ", exp=" + exp +"]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserLoginInfo)) {
			return false;
		}
		UserLoginInfo other = (UserLoginInfo) obj;
		return Objects.equals(token, other.token);
	}

	
}