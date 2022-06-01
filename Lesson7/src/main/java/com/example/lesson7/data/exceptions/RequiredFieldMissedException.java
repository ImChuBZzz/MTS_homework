package com.example.lesson7.data.exceptions;

public class RequiredFieldMissedException extends RuntimeException{
    public RequiredFieldMissedException(String message) {
        super(message);
    }
}
