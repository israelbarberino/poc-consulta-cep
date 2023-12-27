package com.personalprojects.pocconsultacep.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(CepErrorResponse.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CepErrorResponse e) {
        log.error("Failed to process request.", e);
        ExceptionResponse errorResponse = new ExceptionResponse("ERROR_CODE", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(ChangeSetPersister.NotFoundException e) {
        log.error("Resource not found", e);
        ExceptionResponse errorResponse = new ExceptionResponse("NOT_FOUND", "Resource not foud");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Invalid parameters request", e);
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        ExceptionResponse errorResponse = new ExceptionResponse("BAD_REQUEST", errorMessages.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("This request can't be read", e);
        ExceptionResponse errorResponse = new ExceptionResponse("BAD_REQUEST", "This request can't be read");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("Unsupported HTTP method", e);
        ExceptionResponse errorResponse = new ExceptionResponse("METHOD_NOT_ALLOWED", "Unsupported HTTP method");
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        log.error("Internsal server error", e);
        ExceptionResponse errorResponse = new ExceptionResponse("INTERNAL_SERVER_ERROR", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}