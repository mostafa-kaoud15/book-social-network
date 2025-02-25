package com.booksystem.book_social_network.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.booksystem.book_social_network.exception.OperationNotPermittedException;

@RestControllerAdvice
public class OperationNotPermittedHandler {
    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<String> handler(OperationNotPermittedException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
