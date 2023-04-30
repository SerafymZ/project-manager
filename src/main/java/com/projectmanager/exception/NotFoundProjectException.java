package com.projectmanager.exception;

public class NotFoundProjectException extends RuntimeException{
    public NotFoundProjectException(String message) {
        super(message);
    }
}
