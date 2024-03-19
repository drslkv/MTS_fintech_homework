package ru.mtsbank.animals;

import ru.mtsbank.description.AbstractAnimal;
import ru.mtsbank.description.Pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Dog extends Pet {
    public Dog(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    @Override
    public AbstractAnimal createAnimal(String breed, String name, BigDecimal cost,
                                       String character, LocalDate birthDate) {
        return new Dog("Dog" + breed, name, cost, character, birthDate);
    }

    @Override
    public String toString() {
        return "Dog {" +
                "name='" + getName() + '\'' +
                ", cost='" + getCost() + '\'' +
                ", character='" + getCharacter() + '\'' +
                ", birthDate=" + getBirthDate() +
                '}';
    }
}
