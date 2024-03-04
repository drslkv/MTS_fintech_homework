package ru.mtsbank.descriptionAnimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public abstract class AbstractAnimal implements Animal{
    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected String character;
    protected LocalDate birthDate;

    public AbstractAnimal(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
        this.character = character;
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Animal))
            return false;
        Animal animal = (Animal) obj;
        return breed.equals(animal.getBreed()) && name.equals(animal.getName())
                && birthDate.equals(animal.getBirthDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, name, birthDate);
    }
}
