package ru.mtsbank.animals;

import ru.mtsbank.description.AbstractAnimal;
import ru.mtsbank.description.Pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cat extends Pet {
    public Cat(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    @Override
    public AbstractAnimal createAnimal(String breed, String name, BigDecimal cost,
                                       String character, LocalDate birthDate) {
        return new Cat("Cat" + breed, name, cost, character, birthDate);
    }

    @Override
    public String toString() {
        return "Cat {" +
                "name='" + getName() + '\'' +
                ", cost='" + getCost() + '\'' +
                ", character='" + getCharacter() + '\'' +
                ", birthDate=" + getBirthDate() +
                '}';
    }
}
