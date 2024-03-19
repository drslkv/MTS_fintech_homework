package ru.mtsbank.exception;

public class InvalidAnimalTypeException extends IllegalArgumentException {
    public InvalidAnimalTypeException(String message) {
        super(message);
    }
}
