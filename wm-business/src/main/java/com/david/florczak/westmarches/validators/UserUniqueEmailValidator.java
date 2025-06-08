package com.david.florczak.westmarches.validators;

import com.david.florczak.westmarches.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserUniqueEmailValidator implements ConstraintValidator<UserUniqueEmail, String> {
	
	private final UserService userService;
	
	public UserUniqueEmailValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if (value == null) {
			return true;
		}
		
		return !userService.existsByEmail(value.toLowerCase());
	}

}
