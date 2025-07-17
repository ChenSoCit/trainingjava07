package com.java.TrainningJV.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s User not found with id %s = %s", resourceName, fieldName, fieldValue));
    }
}