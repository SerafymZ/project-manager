package com.projectmanager.validation;

import com.projectmanager.repository.TaskTypeRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskTypeValidator implements ConstraintValidator<IsAvailableType, Long> {

    private final TaskTypeRepo taskTypeRepo;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) {
            return false;
        }
        return taskTypeRepo.findTaskTypeById(value).isPresent();
    }
}