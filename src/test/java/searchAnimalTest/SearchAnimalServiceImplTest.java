package searchAnimalTest;

import animals.Cat;
import animals.Dog;
import animals.Shark;
import descriptionAnimal.AbstractAnimal;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import searchAnimal.SearchAnimalService;
import searchAnimal.SearchAnimalServiceImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SearchAnimalServiceImplTest {
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
            assertNotEquals(dog, cat);
        }

        @Test
        @DisplayName("Check equality between two identical animals")
        void equalsIdenticalAnimals() {
            AbstractAnimal cat1 = new Cat("Persian", "Whiskers",
                    BigDecimal.valueOf(300.00), "Playful", LocalDate.of(2018, 5, 15));
            AbstractAnimal cat2 = new Cat("Persian", "Whiskers",
                    BigDecimal.valueOf(300.00), "Playful", LocalDate.of(2018, 5, 15));
            assertEquals(cat1, cat2);
            assertEquals(cat2, cat1);
        }
    }

    // SEARCH
    @Nested
    @DisplayName("Search Method Tests")
    class SearchMethodTests {
        private final SearchAnimalService searchService = new SearchAnimalServiceImpl();
        @Test
        @DisplayName("Check findLeapYearNames method returns correct animal")
        void findLeapYearNamesValidInput() {
            // Arrange
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
        @ValueSource(ints = { 1, 3, 5 })
        @DisplayName("Check findOlderAnimal method with valid input")
        void findOlderAnimal_validInput_shouldReturnCorrectArray(int years) {
            SearchAnimalServiceImpl searchService = new SearchAnimalServiceImpl();
            AbstractAnimal[] animals = {
                    new Cat("Persian", "Whiskers", BigDecimal.valueOf(300.00),
                            "Playful", LocalDate.of(2016, 2, 1)),
                    new Dog("Labrador", "Buddy", BigDecimal.valueOf(500.00),
                            "Friendly", LocalDate.of(2015, 1, 1)),
                    new Cat("Siamese", "Mittens", BigDecimal.valueOf(250.00),
                            "Calm", LocalDate.of(2020, 12, 1)),
            };

            AbstractAnimal[] olderAnimals = searchService.findOlderAnimal(animals, years);

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
                    new Cat("Persian", "Whiskers", BigDecimal.valueOf(250.00), "Calm",
                            LocalDate.of(2020, 12, 1)),
                    new Shark("Great White", "Bruce", BigDecimal.valueOf(1000.00), "Fierce",
                            LocalDate.of(2018, 6, 15)),
            };

            assertDoesNotThrow(() -> searchService.findDuplicate(animals));
        }
    }
}
