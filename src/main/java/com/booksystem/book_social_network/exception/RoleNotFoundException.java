package com.booksystem.book_social_network.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
    public RoleNotFoundException() {
        super("role is not found");
    }
}
