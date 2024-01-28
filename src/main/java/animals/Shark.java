package animals;

import descriptionAnimal.AbstractAnimal;
import descriptionAnimal.Predator;

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
}
