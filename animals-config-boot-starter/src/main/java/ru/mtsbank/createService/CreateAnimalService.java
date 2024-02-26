package ru.mtsbank.createService;

import ru.mtsbank.config.InjectRandomInt;
import ru.mtsbank.descriptionAnimal.AbstractAnimal;
import ru.mtsbank.descriptionAnimal.Animal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public interface CreateAnimalService {
    Set<String> uniqueAnimals = new HashSet<>();
    Random random = new Random();
    @InjectRandomInt(min = 100, max = 1000)
    int N = 1;


    default Map<String, List<Animal>> createAnimals() {
        int iterations = 0;
        Map<String, List<Animal>> animalsMap = new HashMap<>();

        while (uniqueAnimals.size() < 10 && iterations < 100) {
            String type = getRandomAnimalType();
            Animal animalFactory = getRandomAnimalFactory(type);
            AbstractAnimal animal = animalFactory.createAnimal("_" + uniqueAnimals.size(),
                    getRandomName(type),
                    BigDecimal.valueOf(random.nextInt() * N),
                    getRandomCharacter(),
                    randBirthDate());

            animalsMap.computeIfAbsent(type, k -> new ArrayList<>()).add(animal);
            iterations++;
        }

        return animalsMap;
    }

    Map<String, List<Animal>> createAnimals(int n);

    String getRandomCharacter();

    String getRandomName(String type);

    String getRandomAnimalType();
    Animal getRandomAnimalFactory(String type);
    LocalDate randBirthDate();
    void printAnimalDetails(AbstractAnimal animal);
}
