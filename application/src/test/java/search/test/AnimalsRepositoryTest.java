package search.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;
import ru.mtsbank.animals.Shark;
import ru.mtsbank.create.CreateAnimalService;
import ru.mtsbank.description.AbstractAnimal;
import ru.mtsbank.description.Animal;
import ru.mtsbank.exaption.InsufficientAnimalsException;
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
    private CreateAnimalService createAnimalService;
    private AnimalsRepository animalsRepository;
    @BeforeEach
    public void setUp() {
        createAnimalService = mock(CreateAnimalService.class);
        animalsRepository = new AnimalsRepositoryImpl(createAnimalService);
    }

    @DisplayName("Test testFindLeapYearNames()")
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

        when(createAnimalService.createAnimals()).thenReturn(Map.of(
                "Cat", List.of(animal1, animal4),
                "Dog", List.of(animal2, animal5),
                "Shark", List.of(animal3)
        ));

        Map<String, LocalDate> actualLeapYearNames = animalsRepository.findLeapYearNames();

        for (LocalDate birthDate : actualLeapYearNames.values()) {
            assertTrue(birthDate.isLeapYear());
        }
    }

    @DisplayName("Test testFindOlderAnimal()")
    @Test
    public void testFindOlderAnimal() {
        AbstractAnimal animal1 = new Cat("Cat", "Barsik", BigDecimal.ZERO,
                "Friendly", LocalDate.of(2010, 1, 1)); // 14 лет
        AbstractAnimal animal2 = new Dog("Dog", "Rex", BigDecimal.ZERO,
                "Friendly", LocalDate.of(2015, 1, 1)); // 9 лет
        AbstractAnimal animal3 = new Shark("Shark", "Jaws", BigDecimal.ZERO,
                "Aggressive", LocalDate.of(2018, 1, 1)); // 6 лет

        when(createAnimalService.createAnimals()).thenReturn(Map.of(
                "Cat", List.of(animal1),
                "Dog", List.of(animal2),
                "Shark", List.of(animal3)
        ));

        int age = 5;
        Map<AbstractAnimal, Integer> olderAnimals = animalsRepository.findOlderAnimal(age);

        for (Map.Entry<AbstractAnimal, Integer> entry : olderAnimals.entrySet()) {
            Integer animalAge = entry.getValue();

            assertTrue(animalAge > age);
        }
    }

    @DisplayName("Test testFindDuplicate()")
    @Test
    public void testFindDuplicate() {
        Animal animal1 = new Cat("Cat", "Barsik", BigDecimal.ZERO,
                "Friendly", LocalDate.of(2018, 3, 15));
        Animal animal2 = new Cat("Cat", "Barsik", BigDecimal.ZERO,
                "Friendly", LocalDate.of(2018, 3, 15));
        Animal animal3 = new Shark("Shark", "Jaws", BigDecimal.ZERO,
                "Aggressive", LocalDate.of(2018, 1, 1));

        when(createAnimalService.createAnimals()).thenReturn(Map.of(
                "Cat", List.of(animal1, animal2),
                "Shark", List.of(animal3)
        ));

        Map<String, List<Animal>> duplicateMap = animalsRepository.findDuplicate();

        for (List<Animal> animals : duplicateMap.values()) {
            assertEquals(2, animals.size());
            assertEquals(animals.get(0), animals.get(1));
        }
    }

    /* @DisplayName("Test testFindAverageAge()")
    @Test
    public void testFindAverageAge() {
        Animal animal1 = new Cat("Cat", "Barsik", BigDecimal.ZERO,
                "Friendly", LocalDate.of(2010, 1, 1));
        Animal animal2 = new Cat("Cat", "Barsik", BigDecimal.ZERO,
                "Friendly", LocalDate.of(2015, 5, 10));
        Animal animal3 = new Shark("Shark", "Jaws", BigDecimal.ZERO,
                "Aggressive", LocalDate.of(2020, 10, 20));

        when(createAnimalService.createAnimals()).thenReturn(Map.of(
                "Cat", List.of(animal1, animal2),
                "Shark", List.of(animal3)
        ));

        double averageAge = animalsRepository.findAverageAge();

        LocalDate currentDate = LocalDate.of(2024, 3, 18);
        double expectedAverageAges = (
                Period.between(LocalDate.of(2010, 1, 1), currentDate).getYears() +
                Period.between(LocalDate.of(2015, 5, 10), currentDate).getYears() +
                Period.between(LocalDate.of(2020, 10, 20), currentDate).getYears()
        ) / 3.0;

        assertEquals(expectedAverageAges, averageAge);
    } */

    @DisplayName("Test testFindOldAndExpensive()")
    @Test
    public void testFindOldAndExpensive() {
        Animal animal1 = new Cat("Cat", "Barsik", BigDecimal.valueOf(100),
                "Friendly", LocalDate.of(2010, 1, 1));
        Animal animal2 = new Dog("Dog", "Rex", BigDecimal.valueOf(50),
                "Friendly", LocalDate.of(2015, 1, 1));
        Animal animal3 = new Shark("Shark", "Jaws", BigDecimal.valueOf(200),
                "Aggressive", LocalDate.of(2016, 1, 1));

        when(createAnimalService.createAnimals()).thenReturn(Map.of(
                "Cat", List.of(animal1, animal2),
                "Shark", List.of(animal3)
        ));

        List<AbstractAnimal> result = animalsRepository.findOldAndExpensive();

        for (AbstractAnimal animal : result) {
            assertEquals("Jaws", animal.getName());
            assertEquals(BigDecimal.valueOf(200), animal.getCost());
        }
    }

    @DisplayName("Test testFindMinCostAnimals()")
    @Test
    public void testFindMinCostAnimals() throws InsufficientAnimalsException {
        Animal animal1 = new Cat("Cat", "Barsik", BigDecimal.valueOf(50),
                "Friendly", LocalDate.of(2010, 1, 1));
        Animal animal2 = new Dog("Dog", "Rex", BigDecimal.valueOf(30),
                "Friendly", LocalDate.of(2015, 1, 1));
        Animal animal3 = new Shark("Shark", "Jaws", BigDecimal.valueOf(20),
                "Aggressive", LocalDate.of(2016, 1, 1));

        when(createAnimalService.createAnimals()).thenReturn(Map.of(
                "Cat", List.of(animal1, animal2),
                "Shark", List.of(animal3)
        ));

        List<String> result = animalsRepository.findMinCostAnimals();

        String[] expectedNames = {"Barsik", "Rex", "Jaws"};

        for (int i = 0; i < result.size(); i++) {
            assertEquals(expectedNames[i], result.get(i));
        }
    }
}
