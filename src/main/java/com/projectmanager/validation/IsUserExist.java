package com.projectmanager.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsUserExist {

    String MESSAGE = "UserID doesn't exist.";

    String message() default MESSAGE;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}