package ru.mtsbank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@ConfigurationProperties(prefix = "animals")
public class AnimalProperties {
    @Value("${animal.dog.names}")
    private String[] dogName;
    @Value("${animal.cat.names}")
    private String[] catName;
    @Value("${animal.shark.names}")
    private String[] sharkName;
    @Value("${animal.wolf.names}")
    private String[] wolfName;

    @Value("${character}")
    private String[] character;

    private final Random random = new Random();

    public String getDogName() {
        return dogName[random.nextInt(dogName.length)];
    }

    public String getCatName() {
        return catName[random.nextInt(catName.length)];
    }

    public String getSharkName() {
        return sharkName[random.nextInt(sharkName.length)];
    }

    public String getWolfName() {
        return wolfName[random.nextInt(wolfName.length)];
    }

    public String getCharacter() {
        return character[random.nextInt(character.length)];
    }
}
