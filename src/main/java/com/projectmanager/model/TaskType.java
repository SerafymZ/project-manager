package com.projectmanager.model;

import com.projectmanager.exception.NotFoundTaskTypeException;

public enum TaskType {
    MANAGER_TASK(1L),
    TECHNICAL_TASK(2L);

    private static final String EXCEPTION_MESSAGE = "Task status value is invalid: ";

    private Long taskTypeId;

    TaskType(Long taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public Long getTaskTypeId() {
        return taskTypeId;
    }

    public static TaskType fromStringValue(String value) {
        for(TaskType taskType : TaskType.values()) {
            if(taskType.name().equals(value)) {
                return taskType;
            }
        }
        throw new NotFoundTaskTypeException(EXCEPTION_MESSAGE + value);
    }
}