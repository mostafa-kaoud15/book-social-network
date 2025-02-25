package com.booksystem.book_social_network.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.booksystem.book_social_network.exception.InvalidBookException;

@RestControllerAdvice
public class InvalidBookBookExceptionHandler {
    @ExceptionHandler(InvalidBookException.class) 
    public ResponseEntity<String> handler(InvalidBookException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    } 

}
