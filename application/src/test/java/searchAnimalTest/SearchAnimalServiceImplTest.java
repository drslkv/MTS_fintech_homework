package searchAnimalTest;

import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;
import ru.mtsbank.descriptionAnimal.AbstractAnimal;

import java.math.BigDecimal;
import java.time.LocalDate;


public class SearchAnimalServiceImplTest {
    /*
    @Nested
    @DisplayName("Equals Method Tests")
    class EqualsMethodTests {
        @Test
        @DisplayName("Check equality between two different animals")
        void equalsDifferentAnimals() {
            AbstractAnimal cat = new Cat("Persian", "Whiskers",
                    BigDecimal.valueOf(300.00), "Playful", LocalDate.of(2018, 5, 15));
            AbstractAnimal dog = new Dog("Labrador", "Buddy",
                    BigDecimal.valueOf(500.00), "Friendly", LocalDate.of(2017, 8, 22));

            assertNotEquals(cat, dog);
        }

        @Test
        @DisplayName("Check equality between two identical animals")
        void equalsIdenticalAnimals() {
            AbstractAnimal cat1 = new Cat("Persian", "Whiskers",
                    BigDecimal.valueOf(300.00), "Playful", LocalDate.of(2018, 5, 15));
            AbstractAnimal cat2 = new Cat("Persian", "Whiskers",
                    BigDecimal.valueOf(300.00), "Playful", LocalDate.of(2018, 5, 15));
            assertEquals(cat1, cat2);
        }
    }

    // SEARCH
    @Nested
    @DisplayName("Search Method Tests")
    class SearchMethodTests {
        private AnimalsRepository searchService;
        @BeforeEach
        void SearchSetUp() {
            searchService = new AnimalsRepositoryImpl();
        }
        @Test
        @DisplayName("Check findLeapYearNames method returns correct animal")
        void findLeapYearNamesValidInput() {
            AbstractAnimal[] animals = {
                    new Cat("Persian", "Whiskers", BigDecimal.valueOf(300.00), "Playful",
                            LocalDate.of(2016, 2, 29)),
                    new Dog("Labrador", "Buddy", BigDecimal.valueOf(500.00), "Friendly",
                            LocalDate.of(2015, 1, 1)),
                    new Cat("Siamese", "Mittens", BigDecimal.valueOf(250.00), "Calm",
                            LocalDate.of(2020, 12, 1)),
            };

            AbstractAnimal[] leapYearNames = searchService.findLeapYearNames(animals);

            assertEquals(2, leapYearNames.length);
            assertEquals("Whiskers", leapYearNames[0].getName());
            assertEquals("Mittens", leapYearNames[1].getName());
        }

        @ParameterizedTest
        @ValueSource(ints = { 4, 5 })
        @DisplayName("Check findOlderAnimal method with valid input")
        void findOlderAnimalValidInput(int years) {
            AbstractAnimal[] animals = {
                    new Cat("Persian", "Whiskers", BigDecimal.valueOf(300.00),
                            "Playful", LocalDate.of(2016, 2, 1)),
                    new Dog("Labrador", "Buddy", BigDecimal.valueOf(500.00),
                            "Friendly", LocalDate.of(2015, 1, 1)),
                    new Cat("Siamese", "Mittens", BigDecimal.valueOf(250.00),
                            "Calm", LocalDate.of(2020, 12, 1)),
                    new Shark("Great White", "Bruce", BigDecimal.valueOf(1000.00),
                            "Fierce", LocalDate.of(2024, 1, 28)),
                    new Shark("Great White", "Wayne", BigDecimal.valueOf(1000.00),
                            "Fierce", LocalDate.of(2024, 1, 20)),
            };

            AbstractAnimal[] olderAnimals = searchService.findOlderAnimal(animals, years);

            assertEquals(2, olderAnimals.length);

            for (AbstractAnimal animal : olderAnimals) {
                assertTrue(LocalDate.now().minusYears(years).isAfter(animal.getBirthDate()));
            }
        }

        @Test
        @DisplayName("Check findDuplicate method")
        void findDuplicateValidInput() {
            AbstractAnimal[] animals = {
                    new Cat("Persian", "Whiskers", BigDecimal.valueOf(300.00), "Playful",
                            LocalDate.of(2016, 2, 29)),
                    new Dog("Labrador", "Buddy", BigDecimal.valueOf(500.00), "Friendly",
                            LocalDate.of(2015, 1, 1)),
                    new Cat("Persian", "Whiskers", BigDecimal.valueOf(250.00), "Playful",
                            LocalDate.of(2020, 12, 1)),
                    new Shark("Great White", "Bruce", BigDecimal.valueOf(1000.00), "Fierce",
                            LocalDate.of(2018, 6, 15)),
            };


            List<String> findDuplicate = searchService.findDuplicate(animals);

            assertEquals(1, findDuplicate.size());
            assertDoesNotThrow(() -> {
                List<String> duplicates = searchService.findDuplicate(animals);
                assertTrue(duplicates.contains("Duplicate name and character found: Whiskers Playful"));
            });
        }
    } */
}
