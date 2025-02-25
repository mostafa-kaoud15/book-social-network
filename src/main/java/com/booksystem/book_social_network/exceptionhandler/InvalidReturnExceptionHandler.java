package com.booksystem.book_social_network.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.booksystem.book_social_network.exception.InvlaidReturnBookException;

@RestControllerAdvice
public class InvalidReturnExceptionHandler {
    
    @ExceptionHandler(InvlaidReturnBookException.class) 
    public ResponseEntity<String> handler(InvlaidReturnBookException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());

    }

}
