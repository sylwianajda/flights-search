package com.FlightSearch.FlightSearch.controller.exceptions;

import com.FlightSearch.FlightSearch.controller.exceptions.IllegalExceptionProcessing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice(annotations = IllegalExceptionProcessing.class)
public class IllegalExceptionsControllerAdvice {

    @ExceptionHandler({NoSuchElementException.class})
    ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({NonAvailableSeatsException.class})
    ResponseEntity<String> handleNonAvailableSeatsException(NonAvailableSeatsException e){
        String message = "No seats available. Please try booking a different flight";
        return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
    }

}
