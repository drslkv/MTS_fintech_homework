package animals;

import descriptionAnimal.AbstractAnimal;
import descriptionAnimal.Pet;

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
}
