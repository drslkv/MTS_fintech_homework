package search.test;

import org.junit.jupiter.api.Test;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;
import ru.mtsbank.animals.Shark;
import ru.mtsbank.create.CreateAnimalService;
import ru.mtsbank.description.AbstractAnimal;
import ru.mtsbank.search.AnimalsRepository;
import ru.mtsbank.search.AnimalsRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnimalsRepositoryTest {
    @Test
    public void testFindLeapYearNames() {
        AbstractAnimal animal1 = new Cat("Cat", "Barsik", BigDecimal.ZERO,
                "Friendly", LocalDate.of(2012, 1, 1));
        AbstractAnimal animal2 = new Dog("Dog", "Rex", BigDecimal.ZERO,
                "Friendly", LocalDate.of(2016, 1, 1));
        AbstractAnimal animal3 = new Shark("Shark", "Jaws", BigDecimal.ZERO,
                "Aggressive", LocalDate.of(2020, 1, 1));
        AbstractAnimal animal4 = new Cat("Cat", "Whiskers", BigDecimal.ZERO,
                "Friendly", LocalDate.of(2019, 1, 1));
        AbstractAnimal animal5 = new Dog("Dog", "Buddy", BigDecimal.ZERO,
                "Friendly", LocalDate.of(2022, 1, 1));

        CreateAnimalService createAnimalService = mock(CreateAnimalService.class);

        when(createAnimalService.createAnimals()).thenReturn(Map.of(
                "Cat", List.of(animal1, animal4),
                "Dog", List.of(animal2, animal5),
                "Shark", List.of(animal3)
        ));

        AnimalsRepository animalsRepository = new AnimalsRepositoryImpl(createAnimalService);

        Map<String, LocalDate> actualLeapYearNames = animalsRepository.findLeapYearNames();

        for (LocalDate birthDate : actualLeapYearNames.values()) {
            assertTrue(birthDate.isLeapYear());
        }
    }

    @Test
    public void testFindOlderAnimal() {
        AbstractAnimal animal1 = new Cat("Cat", "Barsik", BigDecimal.ZERO,
                "Friendly", LocalDate.of(2010, 1, 1)); // 14 лет
        AbstractAnimal animal2 = new Dog("Dog", "Rex", BigDecimal.ZERO,
                "Friendly", LocalDate.of(2015, 1, 1)); // 9 лет
        AbstractAnimal animal3 = new Shark("Shark", "Jaws", BigDecimal.ZERO,
                "Aggressive", LocalDate.of(2018, 1, 1)); // 6 лет

        CreateAnimalService createAnimalService = mock(CreateAnimalService.class);

        when(createAnimalService.createAnimals()).thenReturn(Map.of(
                "Cat", List.of(animal1),
                "Dog", List.of(animal2),
                "Shark", List.of(animal3)
        ));

        AnimalsRepository animalsRepository = new AnimalsRepositoryImpl(createAnimalService);

        int age = 5;
        Map<AbstractAnimal, Integer> olderAnimals = animalsRepository.findOlderAnimal(age);

        for (Map.Entry<AbstractAnimal, Integer> entry : olderAnimals.entrySet()) {
            Integer animalAge = entry.getValue();

            assertTrue(animalAge > age);
        }
    }
}
