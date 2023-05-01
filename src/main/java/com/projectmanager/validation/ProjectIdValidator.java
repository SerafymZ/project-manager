package com.projectmanager.validation;

import com.projectmanager.repository.ProjectRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProjectIdValidator implements ConstraintValidator<IsProjectExist, Long> {

    private final ProjectRepository projectRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        return projectRepository.findProjectById(value).isPresent();
    }
}