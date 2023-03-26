package com.rs.symphony.controller.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<CampoErro>> validation(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(CampoErro::new).toList());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> validation(ResponseStatusException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record CampoErro(String campo, String mensagem) {
        public CampoErro(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
