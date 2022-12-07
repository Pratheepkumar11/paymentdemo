package com.example.paymentdemo.exception;

public class AlreadyExistingAccountNumberException extends Exception {
    public AlreadyExistingAccountNumberException(String message) {
        super(message);
    }
}
