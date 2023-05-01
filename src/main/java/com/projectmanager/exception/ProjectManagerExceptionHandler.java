package com.projectmanager.exception;

import com.projectmanager.model.dto.ResponseDto;
import com.projectmanager.model.dto.Status;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ProjectManagerExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ResponseDto> handleException(SqlException exception) {
        var response = new ResponseDto(Status.Failed, List.of(exception.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    ResponseEntity<ResponseDto> handleException(NotFoundProjectException exception) {
        var response = new ResponseDto(Status.Failed, List.of(exception.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    ResponseEntity<ResponseDto> handleException(NotFoundTaskException exception) {
        var response = new ResponseDto(Status.Failed, List.of(exception.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    ResponseEntity<ResponseDto> handleException(NotFoundTaskStatusException exception) {
        var response = new ResponseDto(Status.Failed, List.of(exception.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    ResponseEntity<ResponseDto> handleException(NotFoundTaskTypeException exception) {
        var response = new ResponseDto(Status.Failed, List.of(exception.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    ResponseEntity<ResponseDto> handleException(IllegalArgumentException exception) {
        var response = new ResponseDto(Status.Failed, List.of(exception.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> handleException(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            var errorMessage = "Field '" + fieldError.getField() + "' : " + fieldError.getDefaultMessage();
            errors.add(errorMessage);

        }
        var response = new ResponseDto(Status.Failed, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto> handleException(ConstraintViolationException exception) {
        var response = new ResponseDto(Status.Failed, List.of(exception.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}