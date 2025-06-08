package com.david.florczak.westmarches.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UserUniqueEmailValidator.class)
public @interface UserUniqueEmail {
    String message() default "##########UserUniqueEmailValidator##########";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
