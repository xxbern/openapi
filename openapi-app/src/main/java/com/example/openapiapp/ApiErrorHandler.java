package com.example.openapiapp;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.api.model.Error;

@ControllerAdvice
public class ApiErrorHandler {


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Error> notFound(ResponseStatusException rse) {
        return ResponseEntity.status(rse.getStatusCode()).body(new Error().message(rse.getReason()));
    }

}
