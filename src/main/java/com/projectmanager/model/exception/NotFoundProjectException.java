package com.projectmanager.model.exception;

public class NotFoundProjectException extends RuntimeException{
    public NotFoundProjectException(String message) {
        super(message);
    }
}
