package com.coursuasz.l32i.daos.microserviceutilisateur.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static com.coursuasz.l32i.daos.microserviceutilisateur.exception.ErrorHttpResponse.response;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException exception) {
        return response(HttpStatus.NOT_FOUND, exception.getMessage(), null);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<Object> resourceAlreadyExistException(ResourceAlreadyExistException exception) {
        return response(HttpStatus.CONFLICT, exception.getMessage(), null);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> runtimeException(RuntimeException exception) {
        return response(HttpStatus.FORBIDDEN, exception.getMessage(), null);
    }
}