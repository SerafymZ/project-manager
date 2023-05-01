package com.projectmanager.validation;

import com.projectmanager.repository.TaskStatusRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskStatusValidator implements ConstraintValidator<IsAvailableStatus, Long> {

    private final TaskStatusRepository taskStatusRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {

        if(value == null) {
            return false;
        }

        return taskStatusRepository.findTaskStatusById(value).isPresent();
    }
}