package ru.mtsbank.exaption;

public class InvalidAgeException extends IllegalArgumentException {
    public InvalidAgeException(String message) {
        super(message);
    }
}