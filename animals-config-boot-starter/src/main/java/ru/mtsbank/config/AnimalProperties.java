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
        String[] names = dogName.split(",");
        return names[random.nextInt(names.length)].trim();
    }

    public String getCatName() {
        String[] names = catName.split(",");
        return names[random.nextInt(names.length)].trim();
    }

    public String getSharkName() {
        String[] names = sharkName.split(",");
        return names[random.nextInt(names.length)].trim();
    }

    public String getWolfName() {
        String[] names = wolfName.split(",");
        return names[random.nextInt(names.length)].trim();
    }
}
