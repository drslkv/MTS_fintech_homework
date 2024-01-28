package animals;

import descriptionAnimal.AbstractAnimal;
import descriptionAnimal.Pet;

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
}
