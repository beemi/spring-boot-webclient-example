package com.jaitechltd.webclientsspringbootexample.exception;

public class PostCodeFormatException extends RuntimeException {

    public PostCodeFormatException(String message) {
        super(message);
    }

    public PostCodeFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
