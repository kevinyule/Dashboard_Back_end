package com.dashboard_gk.dashboard_gk.exception;

import com.dashboard_gk.dashboard_gk.response.ObjectResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ObjectResponse> handleValidationException(MethodArgumentNotValidException ex) {
        ObjectResponse response = new ObjectResponse();
        response.setStatusCode(-1);

        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        response.setMessage("Error: " + errorMessage);
        return ResponseEntity.badRequest().body(response);
    }
}
