package com.microedge.exceptions;

public class MessageNotReadableException extends RuntimeException {

    public MessageNotReadableException() {
        super("Invalid Data. Please check.");
    }
}
