import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.mtsbank.config.AnimalConfiguration;
import ru.mtsbank.createService.CreateAnimalService;
import ru.mtsbank.descriptionAnimal.AbstractAnimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AnimalConfiguration.class)
@TestPropertySource(locations = "classpath:application-test.yml")
class AnimalStarterTests {

    @Autowired
    private CreateAnimalService createAnimalService;

    @Test
    void testCreateAnimal() {
        AbstractAnimal[] animals = createAnimalService.createAnimals();
        assertNotNull(animals);

        for (AbstractAnimal animal : animals) {
            assertTrue(animal.getBreed().matches("(Cat|Dog|Shark|Wolf)_\\d+"));
            assertNotNull(animal.getName());
            assertNotNull(animal.getCharacter());
        }
    }

    @Value("${animal.dog.names}")
    private String[] dogName;
    @Value("${animal.cat.names}")
    private String[] catName;
    @Value("${animal.shark.names}")
    private String[] sharkName;
    @Value("${animal.wolf.names}")
    private String[] wolfName;

    @Test
    void testAnimalNames() {
        AbstractAnimal[] animals = createAnimalService.createAnimals();
        assertNotNull(animals);

        assertNotNull(dogName);
        assertNotNull(catName);
        assertNotNull(sharkName);
        assertNotNull(wolfName);

        for (AbstractAnimal animal : animals) {
            String name = null;
            String breed = animal.getBreed().split("_")[0];
            switch (breed) {
                case "Dog":
                    name = getRandomName(dogName);
                    break;
                case "Cat":
                    name = getRandomName(catName);
                    break;
                case "Shark":
                    name = getRandomName(sharkName);
                    break;
                case "Wolf":
                    name = getRandomName(wolfName);
                    break;
            }
            assertNotNull(name);
            assertFalse(name.isEmpty());

            assertTrue(name.matches("(Bailey|Cooper|Daisy|Charlie|Max|Suzanne" +
                    "|Gerry|Jim|Victor|Vladimir|Puffy)"));
        }
    }

    private String getRandomName(String[] names) {
        int index = (int) (Math.random() * names.length);
        return names[index];
    }
}