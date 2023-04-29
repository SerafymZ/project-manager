package com.projectmanager.model.dto;

public enum Status {
    OK(1),
    Failed(2),
    Forbidden(3);

    public Integer status;

    Status(Integer status) {
        this.status = status;
    }
}