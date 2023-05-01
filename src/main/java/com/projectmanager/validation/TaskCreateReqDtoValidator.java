package com.projectmanager.validation;

import com.projectmanager.model.dto.task.TaskCreateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskCreateReqDtoValidator implements CustomValidator{

    private final CommonTaskDtoValidator commonTaskDtoValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return TaskCreateReqDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target) {
        TaskCreateReqDto dto = (TaskCreateReqDto) target;

        commonTaskDtoValidator.validate(dto.getTaskTypeId(), dto.getBranch(), dto.getManagerDocs());
    }
}