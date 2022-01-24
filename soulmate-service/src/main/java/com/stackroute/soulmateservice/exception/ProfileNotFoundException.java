package com.stackroute.soulmateservice.exception;

public class ProfileNotFoundException extends Exception{
    private String message;

    public ProfileNotFoundException() {
    }

    public ProfileNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}

