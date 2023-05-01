package com.projectmanager.model;

import com.projectmanager.exception.NotFoundTaskStatusException;

public enum TaskStatus {
    NEW(1L),
    PROGRESS(2L),
    DONE(3L);

    private static final String EXCEPTION_MESSAGE = "Task status value is invalid: ";

    private final Long statusId;

    TaskStatus(Long statusId) {
        this.statusId = statusId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public static TaskStatus fromStringValue(String value) {
        for(TaskStatus status : TaskStatus.values()) {
            if(status.name().equals(value)) {
                return status;
            }
        }
        throw new NotFoundTaskStatusException(EXCEPTION_MESSAGE + value);
    }
}
