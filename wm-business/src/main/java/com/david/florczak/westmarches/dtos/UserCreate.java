package com.david.florczak.westmarches.dtos;

import java.util.Objects;

import com.david.florczak.westmarches.validators.UserUniqueEmail;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreate(
		@NotBlank @Size(max = 255) @UserUniqueEmail
		@Pattern(regexp = "^(?=.{1,64}@)\\w+([\\.-]?\\w+)*@(?=.{4,252}$)\\w+([\\.-]?\\w+)*(\\.\\w{2,4})+$") 
		String email, 
		@NotBlank @Size(max = 60) 
		@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[;:\\*\\-!]).{8,}$") 
		String password, 
		@NotBlank @Size(max = 50) 
		String username) 
{

	@Override
	public String toString() {
		return "UserCreate [email=" + email + ", password=[PROTECTED], username=" + username+ "]";
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
		if (!(obj instanceof UserCreate)) {
			return false;
		}
		UserCreate other = (UserCreate) obj;
		return Objects.equals(email, other.email);
	}
}
