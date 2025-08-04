package com.david.florczak.westmarches.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueByPropertyValidator.class)
public @interface UniqueByProperty {

	String message() default "UniqueByPropertyValidator";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

	String fieldName();
}
