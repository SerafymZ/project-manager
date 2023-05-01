package com.projectmanager.validation;

import com.projectmanager.model.TaskType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class TaskTypeValidator implements ConstraintValidator<AvailableTypeValue, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        for (TaskType type : TaskType.values()) {
            if(type.name().equals(value)) {
                return true;
            }
        }

        return false;
    }
}