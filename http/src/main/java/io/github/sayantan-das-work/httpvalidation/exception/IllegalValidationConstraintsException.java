package com.validation.http.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR,reason = "Invalid validation constraints provided")
public class IllegalValidationConstraintsException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public IllegalValidationConstraintsException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return String.format("[%s] [%s] :: [%s]", httpStatus.value(), httpStatus.getReasonPhrase(), message);
    }
}
