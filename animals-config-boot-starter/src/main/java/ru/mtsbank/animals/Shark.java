package ru.mtsbank.animals;

import ru.mtsbank.descriptionAnimal.AbstractAnimal;
import ru.mtsbank.descriptionAnimal.Predator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Shark extends Predator {
    public Shark(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    @Override
    public AbstractAnimal createAnimal(String breed, String name, BigDecimal cost,
                                       String character, LocalDate birthDate) {
        return new Shark("Shark" + breed, name, cost, character, birthDate);
    }

    @Override
    public String toString() {
        return "Shark {" +
                "name='" + getName() + '\'' +
                ", cost='" + getCost() + '\'' +
                ", character='" + getCharacter() + '\'' +
                ", birthDate=" + getBirthDate() +
                '}';
    }
}
