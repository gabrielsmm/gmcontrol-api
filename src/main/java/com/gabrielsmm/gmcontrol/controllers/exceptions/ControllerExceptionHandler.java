package com.gabrielsmm.gmcontrol.controllers.exceptions;

import com.gabrielsmm.gmcontrol.services.exceptions.AuthorizationException;
import com.gabrielsmm.gmcontrol.services.exceptions.DataIntegrityException;
import com.gabrielsmm.gmcontrol.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErroPadrao> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<ErroPadrao> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de dados", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ErroValidacao erro = new ErroValidacao(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", e.getMessage(), request.getRequestURI());
        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            erro.addErro(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErroPadrao> authorization(AuthorizationException e, HttpServletRequest request) {
        ErroPadrao erro = new ErroPadrao(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
    }

}
