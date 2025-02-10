package com.john.spring.controller;


import com.john.spring.exceptions.DataNotValidatedException;
import com.john.spring.exceptions.InstanceUndefinedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsController {

    @ExceptionHandler (DataNotValidatedException.class )
    public ResponseEntity<String> handleDataNotValidatedException (Error error){

        return new ResponseEntity<>(error.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler (InstanceUndefinedException.class )
    public ResponseEntity<String> handleInstanceUndefinedException (Error error){

        return new ResponseEntity<>(error.getMessage(), HttpStatus.NOT_FOUND);
    }
}
