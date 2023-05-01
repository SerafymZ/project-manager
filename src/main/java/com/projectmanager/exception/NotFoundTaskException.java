package com.projectmanager.exception;

public class NotFoundTaskException extends RuntimeException{
    public NotFoundTaskException(String message) {
        super(message);
    }
}
