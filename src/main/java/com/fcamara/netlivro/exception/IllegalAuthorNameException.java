package com.fcamara.netlivro.exception;

import org.springframework.http.HttpStatus;

public class IllegalAuthorNameException extends RuntimeException {
    private HttpStatus statusCode = HttpStatus.BAD_REQUEST;
    public IllegalAuthorNameException(String message) {
        super(message);
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
