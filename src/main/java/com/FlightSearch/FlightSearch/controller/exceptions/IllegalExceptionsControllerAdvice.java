package com.FlightSearch.FlightSearch.controller.exceptions;

import com.FlightSearch.FlightSearch.controller.exceptions.IllegalExceptionProcessing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice(annotations = IllegalExceptionProcessing.class)
public class IllegalExceptionsControllerAdvice {

    @ExceptionHandler({NoSuchElementException.class})
    ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e){
        return ResponseEntity.notFound().build();
        //return ResponseEntity.badRequest().body(e.getMessage());
    }
}
