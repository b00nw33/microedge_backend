// src/main/java/com/microedge/exceptions/ResourceNotFoundException.java
package com.microedge.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}