package com.projectmanager.validation;

import com.projectmanager.model.TaskStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
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