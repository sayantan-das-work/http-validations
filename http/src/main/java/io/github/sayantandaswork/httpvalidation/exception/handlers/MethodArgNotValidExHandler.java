package io.github.sayantandaswork.httpvalidation.exception.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MethodArgNotValidExHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgNotValidEx(MethodArgumentNotValidException exception)
    {
        Map<String,String> errorMap = new HashMap<>();
        exception.getFieldErrors().forEach(fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errorMap);
    }
}
