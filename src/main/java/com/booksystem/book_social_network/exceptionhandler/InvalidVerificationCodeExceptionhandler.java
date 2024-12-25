package com.booksystem.book_social_network.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.booksystem.book_social_network.exception.InvalidVerificationCodeException;

@ControllerAdvice
public class InvalidVerificationCodeExceptionhandler {
    @ExceptionHandler(InvalidVerificationCodeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handler(InvalidVerificationCodeException ex) {
        return ex.getMessage();
    }
}

