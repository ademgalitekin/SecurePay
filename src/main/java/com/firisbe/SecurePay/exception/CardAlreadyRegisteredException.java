package com.firisbe.SecurePay.exception;

public class CardAlreadyRegisteredException extends RuntimeException {
    public CardAlreadyRegisteredException(String message) {
        super(message);
    }
}
