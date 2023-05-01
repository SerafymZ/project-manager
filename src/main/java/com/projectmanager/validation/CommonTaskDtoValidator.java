package com.projectmanager.validation;

import com.projectmanager.exception.NotFoundTaskTypeException;
import com.projectmanager.model.TaskType;
import com.projectmanager.model.entity.TaskTypeEntity;
import com.projectmanager.repository.TaskTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommonTaskDtoValidator {

    private static final String EXCEPTION_MESSAGE = "Task status value is invalid: ";
    private static final String BRANCH_INVALID_VALUE_MESSAGE =
            "Field branch must be null. This field only for TECHNICAL_TASK type";
    private static final String MANAGER_DOCS_INVALID_VALUE_MESSAGE =
            "Field managerDocs must be null. This field only for MANAGER_TASK type";


    private final TaskTypeRepo taskTypeRepo;

    public void validate(Long taskTypeId, String branch, String managerDocs) {

        TaskTypeEntity taskType = taskTypeRepo.findTaskTypeById(taskTypeId)
                .orElseThrow(() -> new NotFoundTaskTypeException(EXCEPTION_MESSAGE + taskTypeId));

        if(TaskType.MANAGER_TASK.name().equals(taskType.getTaskType()) && branch != null) {
            throw new IllegalArgumentException(BRANCH_INVALID_VALUE_MESSAGE);
        } else if (TaskType.TECHNICAL_TASK.name().equals(taskType.getTaskType()) && managerDocs != null) {
            throw new IllegalArgumentException(MANAGER_DOCS_INVALID_VALUE_MESSAGE);
        }

    }
}