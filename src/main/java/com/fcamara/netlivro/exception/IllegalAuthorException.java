package com.fcamara.netlivro.exception;

import org.springframework.http.HttpStatus;

public class IllegalAuthorException extends RuntimeException {
    private HttpStatus statusCode = HttpStatus.BAD_REQUEST;
    public IllegalAuthorException(String message) {
        super(message);
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
