package com.example.ohs.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.validation.FieldError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleValidation(MethodArgumentNotValidException ex){
        Map<String,Object> body = new HashMap<>();
        List<Map<String,String>> errors = new ArrayList<>();
        for(FieldError fe : ex.getBindingResult().getFieldErrors()){
            Map<String,String> err = new HashMap<>();
            err.put("field", fe.getField());
            err.put("message", fe.getDefaultMessage());
            errors.add(err);
        }
        body.put("type", "https://example.org/validation-error");
        body.put("title", "Validation failed");
        body.put("status", 400);
        body.put("errors", errors);
        return body;
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> handleNotFound(NoSuchElementException ex){
        Map<String,Object> m = new HashMap<>();
        m.put("title", "Not Found");
        m.put("status", 404);
        m.put("detail", ex.getMessage());
        return m;
    }
}
