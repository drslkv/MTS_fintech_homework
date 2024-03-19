package starterTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import ru.mtsbank.create.CreateAnimalService;

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
}
