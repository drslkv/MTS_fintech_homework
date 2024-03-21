package ru.mtsbank.exception;

public class InvalidAnimalException extends IllegalArgumentException {
    public InvalidAnimalException(String message) {
        super(message);
    }
}
