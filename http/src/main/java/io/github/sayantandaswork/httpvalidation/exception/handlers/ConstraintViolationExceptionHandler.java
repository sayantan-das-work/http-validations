package io.github.sayantandaswork.httpvalidation.exception.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ConstraintViolationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationEx(ConstraintViolationException ex)
    {
        Map<String,String> errorMap = new HashMap<>();
        ex.getConstraintViolations().forEach(constraintViolation ->
                errorMap.put(
                        constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage()
                ));
        return ResponseEntity.badRequest().body(errorMap);
    }
}
