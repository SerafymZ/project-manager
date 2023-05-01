package com.projectmanager.exception;

public class NotFoundTaskStatusException extends RuntimeException{
    public NotFoundTaskStatusException(String message) {
        super(message);
    }
}
