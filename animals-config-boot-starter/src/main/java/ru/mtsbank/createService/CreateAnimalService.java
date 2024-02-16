package ru.mtsbank.createService;

import ru.mtsbank.descriptionAnimal.AbstractAnimal;
import ru.mtsbank.descriptionAnimal.Animal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public interface CreateAnimalService {
    Set<String> uniqueAnimals = new HashSet<>();
    Random random = new Random();
    String[] names = {"Buddy", "Max", "Charlie", "Bella", "Lucy", "Daisy", "Rocky", "Luna"};
    String[] character = {"Aggressive", "Fierce", "Friendly", "Playful"};

    default AbstractAnimal[] createAnimals() {
        int iterations = 0;
        List<AbstractAnimal> animalsList = new ArrayList<>();

        while (uniqueAnimals.size() < 10) {
            String type = getRandomAnimalType();
            Animal animalFactory = getRandomAnimalFactory(type);
            AbstractAnimal animal = animalFactory.createAnimal("_" + uniqueAnimals.size(),
                    names[random.nextInt(names.length)],
                    BigDecimal.valueOf(random.nextDouble() * 1000),
                    character[random.nextInt(character.length)],
                    randBirthDate());

            if (uniqueAnimals.add(animal.getBreed())) {
                animalsList.add(animal);
            } else {
                System.out.println("Not added: " + animal.getBreed());
            }

            iterations++;
        }

        return animalsList.toArray(new AbstractAnimal[0]);
    }
    AbstractAnimal[] createAnimals(int n);
    String getRandomAnimalType();
    Animal getRandomAnimalFactory(String type);
    LocalDate randBirthDate();
    void printAnimalDetails(AbstractAnimal animal);
}
