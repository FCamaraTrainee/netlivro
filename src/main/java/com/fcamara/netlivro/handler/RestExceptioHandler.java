package com.fcamara.netlivro.handler;

import com.fcamara.netlivro.exception.IllegalAuthorException;
import com.fcamara.netlivro.exception.IllegalBookException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptioHandler {
    @ExceptionHandler(IllegalAuthorException.class)
    public static ResponseEntity<String> IllegalArgumentExceptionHandler(IllegalAuthorException ex) {
        String message = ex.getMessage();
        System.out.println("Deu erro aqui gente!!!");
        return new ResponseEntity<>(message, ex.getStatusCode());
    }
    @ExceptionHandler(IllegalBookException.class)
    public static ResponseEntity<String> IllegalArgumentExceptionHandler(IllegalBookException ex) {
        String message = ex.getMessage();
        System.out.println("Deu erro aqui gente!!!");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
