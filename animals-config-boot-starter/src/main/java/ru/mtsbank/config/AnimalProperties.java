package ru.mtsbank.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static ru.mtsbank.service.CreateAnimalService.random;

@ConfigurationProperties(prefix = "animals")
public class AnimalProperties {
    private String dogName;
    private String catName;
    private String sharkName;
    private String wolfName;

    public String getDogName() {
        return dogName;
    }

    public String getCatName() {
        return catName;
    }

    public String getSharkName() {
        return sharkName;
    }

    public String getWolfName() {
        return wolfName;
    }
}
