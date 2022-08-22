package com.apicadastro.core.exception.resource;

import com.apicadastro.core.exception.service.DatabaseException;
import com.apicadastro.core.exception.service.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

import static com.apicadastro.core.consts.Consts.DATABASE_EXCEPTION;
import static com.apicadastro.core.consts.Consts.RESOURCE_NOT_FOUND;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        HttpStatus status = objectsFactory(request, error, HttpStatus.NOT_FOUND, RESOURCE_NOT_FOUND, e.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        HttpStatus status = objectsFactory(request, error, HttpStatus.BAD_REQUEST, DATABASE_EXCEPTION, e.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    private HttpStatus objectsFactory(HttpServletRequest request, StandardError error, HttpStatus badRequest, String databaseException, String message) {
        HttpStatus status = badRequest;
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError(databaseException);
        error.setMesssage(message);
        error.setPath(request.getRequestURI());
        return status;
    }

}
