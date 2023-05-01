package com.projectmanager.validation;

import com.projectmanager.model.TaskStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class TaskStatusValidator implements ConstraintValidator<AvailableStatusValue, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        for(TaskStatus status : TaskStatus.values()) {
            if(status.name().equals(value)) {
                return true;
            }
        }

        return false;
    }
}