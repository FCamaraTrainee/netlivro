package com.fcamara.netlivro.handler;

import com.fcamara.netlivro.exception.IllegalAuthorNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptioHandler {
    @ExceptionHandler(IllegalAuthorNameException.class)
    public static ResponseEntity<String> IllegalArgumentExceptionHandler(IllegalAuthorNameException ex) {
        String message = ex.getMessage();
        System.out.println("Deu erro aqui gente!!!");
        return new ResponseEntity<>(message, ex.getStatusCode());
    }
}
