package ru.mtsbank.animals;

import ru.mtsbank.descriptionAnimal.AbstractAnimal;
import ru.mtsbank.descriptionAnimal.Predator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Wolf extends Predator {
    public Wolf(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    @Override
    public AbstractAnimal createAnimal(String breed, String name, BigDecimal cost,
                                       String character, LocalDate birthDate) {
        return new Wolf("Wolf" + breed, name, cost, character, birthDate);
    }
}
