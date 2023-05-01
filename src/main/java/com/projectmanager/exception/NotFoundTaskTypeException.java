package com.projectmanager.exception;

public class NotFoundTaskTypeException extends RuntimeException{
    public NotFoundTaskTypeException(String message) {
        super(message);
    }
}