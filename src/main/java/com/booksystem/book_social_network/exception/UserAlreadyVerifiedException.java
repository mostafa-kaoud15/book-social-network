package com.booksystem.book_social_network.exception;

public class UserAlreadyVerifiedException extends RuntimeException {
    public UserAlreadyVerifiedException(String username) {
        super("user already exsits : " + username);
    }
    public UserAlreadyVerifiedException() {
        super("user already exsits" );
    }


}
