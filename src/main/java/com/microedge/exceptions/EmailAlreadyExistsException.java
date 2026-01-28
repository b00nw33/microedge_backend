// src/main/java/com/microedge/exceptions/EmailAlreadyExistsException.java
package com.microedge.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("Email already exists: " + email);
    }
}