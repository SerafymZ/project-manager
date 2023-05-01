package com.projectmanager.model;

public enum TaskStatus {
    NEW(1L),
    PROGRESS(2L),
    DONE(3L);

    private final Long statusId;

    TaskStatus(Long statusId) {
        this.statusId = statusId;
    }

    public Long getStatusId() {
        return statusId;
    }
}
