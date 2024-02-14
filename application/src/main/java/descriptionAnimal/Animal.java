package descriptionAnimal;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Animal {
    String getBreed();
    String getName();
    BigDecimal getCost();
    String getCharacter();
    LocalDate getBirthDate();
    boolean equals(Object obj);

    AbstractAnimal createAnimal(String breed, String name, BigDecimal cost,
                                String character, LocalDate birthDate);
}
