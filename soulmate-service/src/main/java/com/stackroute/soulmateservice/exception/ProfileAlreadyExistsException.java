package com.stackroute.soulmateservice.exception;

public class ProfileAlreadyExistsException extends Exception{
    private String message;
    public ProfileAlreadyExistsException() {
    }

    public ProfileAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
