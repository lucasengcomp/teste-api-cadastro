package com.apicadastro.core.exception.resource;

import com.apicadastro.core.exception.service.ConstraintViolationException;
import com.apicadastro.core.exception.service.DatabaseException;
import com.apicadastro.core.exception.service.ResourceNotFoundException;
import com.apicadastro.core.exception.service.UnprocessableEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

import static com.apicadastro.core.consts.Consts.*;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        HttpStatus status = objectsFactory(request, error, HttpStatus.NOT_FOUND, RECURSO_NAO_ENCONTRADO, e.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        HttpStatus status = objectsFactory(request, error, HttpStatus.BAD_REQUEST, EXCECAO_DE_BANCO_DE_DADOS, e.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> violationException(ConstraintViolationException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        HttpStatus status = objectsFactory(request, error, HttpStatus.INTERNAL_SERVER_ERROR, EXCECAO_DE_BANCO_DE_DADOS_VIOLATION, e.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(UnprocessableEntity.class)
    public ResponseEntity<StandardError> unprocessableEntity(UnprocessableEntity e, HttpServletRequest request) {
        StandardError error = new StandardError();
        HttpStatus status = objectsFactory(request, error, HttpStatus.UNPROCESSABLE_ENTITY, ENTIDADE_NAO_PODE_SER_PROCESSADA, e.getMessage());
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
