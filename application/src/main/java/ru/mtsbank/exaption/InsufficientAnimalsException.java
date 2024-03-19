package ru.mtsbank.exaption;

public class InsufficientAnimalsException extends Exception {
    public InsufficientAnimalsException(String message) {
        super(message);
    }
}
