package com.booksystem.book_social_network.exception;

public class InvalidVerificationCodeException extends RuntimeException {
    public InvalidVerificationCodeException(String message) {
        super(message);
    }
    public InvalidVerificationCodeException() {
        super("invalid verification code");
    }
}
