package test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;
import ru.mtsbank.animals.Shark;
import ru.mtsbank.createService.CreateAnimalService;
import ru.mtsbank.descriptionAnimal.AbstractAnimal;
import ru.mtsbank.searchAnimal.AnimalsRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestConfig.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
class AnimalStarterTests {
    @Autowired
    private CreateAnimalService createAnimalService;

    @Test
    public void testGetRandomAnimalType() {
        String animalType = createAnimalService.getRandomAnimalType();
        assertNotNull(animalType);
        assertTrue(animalType.equals("Cat") || animalType.equals("Dog") ||
                animalType.equals("Shark") || animalType.equals("Wolf"));
    }

    @Test
    public void testGetRandomName() {
        String catName = createAnimalService.getRandomName("Cat");
        assertNotNull(catName);
        assertTrue(catName.equals("Whiskers") || catName.equals("Mittens") || catName.equals("Luna"));

        String dogName = createAnimalService.getRandomName("Dog");
        assertNotNull(dogName);
        assertTrue(dogName.equals("Bailey") || dogName.equals("Cooper") ||
                dogName.equals("Daisy") || dogName.equals("Aba"));

        String sharkName = createAnimalService.getRandomName("Shark");
        assertNotNull(sharkName);
        assertTrue(sharkName.equals("Bruce") || sharkName.equals("Nemo") || sharkName.equals("Dory"));

        String wolfName = createAnimalService.getRandomName("Wolf");
        assertNotNull(wolfName);
        assertTrue(wolfName.equals("Luna") || wolfName.equals("Shadow") || wolfName.equals("Rocky"));
    }

    @Test
    void testFindLeapYearNames() {
        AnimalsRepository animalsRepository = Mockito.mock(AnimalsRepository.class);

        Map<String, LocalDate> expected = new HashMap<>();
        expected.put("Cat Whiskers", LocalDate.of(2000, 2, 29));

        Mockito.when(animalsRepository.findLeapYearNames()).thenReturn(expected);

        Map<String, LocalDate> result = animalsRepository.findLeapYearNames();

        assertEquals(expected, result);
    }

    @Test
    void testFindOlderAnimal() {
        AbstractAnimal cat = new Cat("Cat", "Whiskers", BigDecimal.valueOf(1000),
                "Friendly",
                LocalDate.of(2010, 1, 1));
        AbstractAnimal dog = new Dog("Dog", "Bailey", BigDecimal.valueOf(1500),
                "Playful",
                LocalDate.of(2015, 6, 15));
        AbstractAnimal shark = new Shark("Shark", "Bruce", BigDecimal.valueOf(2000),
                "Aggressive",
                LocalDate.of(2012, 4, 20));

        AnimalsRepository animalsRepository = Mockito.mock(AnimalsRepository.class);

        Mockito.when(animalsRepository.findOlderAnimal(Mockito.anyInt()))
                .thenReturn(Map.of(cat, 14, dog, 8, shark, 11));

        Map<AbstractAnimal, Integer> result = animalsRepository.findOlderAnimal(7);

        assertEquals(14, result.get(cat));
        assertEquals(8, result.get(dog));
        assertEquals(11, result.get(shark));
    }

    @Test
    void testFindDuplicate() {
        List<AbstractAnimal> animals = new ArrayList<>();
        animals.add(new Cat("Cat", "Whiskers", BigDecimal.valueOf(100.00), "Friendly",
                LocalDate.of(2010, 1, 1)));
        animals.add(new Dog("Dog", "Bailey", BigDecimal.valueOf(120.00), "Playful",
                LocalDate.of(2015, 6, 15)));
        animals.add(new Cat("Cat", "Whiskers", BigDecimal.valueOf(100.00), "Friendly",
                LocalDate.of(2011, 2, 2)));
        animals.add(new Shark("Shark", "Bruce", BigDecimal.valueOf(150.00), "Aggressive",
                LocalDate.of(2012, 4, 20)));

        AnimalsRepository animalsRepository = Mockito.mock(AnimalsRepository.class);

        Map<String, Integer> expectedDuplicates = new HashMap<>();
        expectedDuplicates.put("Cat Whiskers Friendly", 2);

        Mockito.when(animalsRepository.findDuplicate()).thenReturn(expectedDuplicates);

        Map<String, Integer> result = animalsRepository.findDuplicate();

        assertEquals(expectedDuplicates, result);
    }
}
