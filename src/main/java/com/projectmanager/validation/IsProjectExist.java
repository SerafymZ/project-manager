package com.projectmanager.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProjectIdValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsProjectExist {

    String MESSAGE = "ProjectID doesn't exist.";

    String message() default MESSAGE;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
