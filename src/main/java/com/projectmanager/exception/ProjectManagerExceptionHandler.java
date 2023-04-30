package com.projectmanager.exception;

import com.projectmanager.model.dto.ResponseDto;
import com.projectmanager.model.dto.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectManagerExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ResponseDto> handleException(SqlException exception) {
        var response = new ResponseDto(Status.Failed, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
