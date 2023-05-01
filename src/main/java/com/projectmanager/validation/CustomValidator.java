package com.projectmanager.validation;

public interface CustomValidator {
    boolean supports(Class<?> clazz);
    void validate(Object target);
}