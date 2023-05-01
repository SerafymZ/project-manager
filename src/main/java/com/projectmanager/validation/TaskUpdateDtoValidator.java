package com.projectmanager.validation;

import com.projectmanager.model.dto.task.TaskUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskUpdateDtoValidator implements CustomValidator{

    private final CommonTaskDtoValidator commonTaskDtoValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return TaskUpdateDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target) {
        TaskUpdateDto dto = (TaskUpdateDto) target;

        commonTaskDtoValidator.validate(dto.getTaskTypeId(), dto.getBranch(), dto.getManagerDocs());
    }
}