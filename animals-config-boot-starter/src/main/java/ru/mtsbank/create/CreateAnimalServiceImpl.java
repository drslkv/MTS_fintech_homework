package ru.mtsbank.create;

import org.springframework.stereotype.Service;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;
import ru.mtsbank.animals.Shark;
import ru.mtsbank.animals.Wolf;
import ru.mtsbank.config.AnimalProperties;
import ru.mtsbank.config.InjectRandomInt;
import ru.mtsbank.description.AbstractAnimal;
import ru.mtsbank.description.Animal;
import ru.mtsbank.exception.InvalidAnimalException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CreateAnimalServiceImpl implements CreateAnimalService {
    private final AnimalProperties animalProperties;

    @InjectRandomInt(min = 1000, max = 10000)
    private final int N = 1;

    public CreateAnimalServiceImpl(AnimalProperties animalProperties) {
        this.animalProperties = animalProperties;
    }

    public String getRandomAnimalType() {
        String[] animalTypes = {"Cat", "Dog", "Shark", "Wolf"};
        return animalTypes[new Random().nextInt(animalTypes.length)];
    }

    public String getRandomName(String type) {
        switch (type) {
            case "Cat":
                return animalProperties.getCatName();
            case "Dog":
                return animalProperties.getDogName();
            case "Shark":
                return animalProperties.getSharkName();
            case "Wolf":
                return animalProperties.getWolfName();
            default:
                throw new InvalidAnimalException("Error animal type: " + type);
        }
    }

    public String getRandomCharacter() {
        return animalProperties.getCharacter();
    }

    public LocalDate randBirthDate() {
        LocalDate startDate = LocalDate.of(2010, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomEpochDay);
    }

    public Animal getRandomAnimalFactory(String type) {
        if (isValidAnimalType(type)) {
            throw new InvalidAnimalException("Invalid animal type: " + type);
        }
        String name = getRandomName(type);
        String character = getRandomCharacter();
        LocalDate birthDate = randBirthDate();

        switch (type) {
            case "Cat":
                return new Cat("Cat", name, BigDecimal.valueOf(random.nextDouble() * N),
                        character, birthDate);
            case "Dog":
                return new Dog("Dog", name, BigDecimal.valueOf(random.nextDouble() * N),
                        character, birthDate);
            case "Shark":
                return new Shark("Shark", name, BigDecimal.valueOf(random.nextDouble() * N),
                        character, birthDate);
            case "Wolf":
                return new Wolf("Wolf", name, BigDecimal.valueOf(random.nextDouble() * N),
                        character, birthDate);
            default:
                throw new InvalidAnimalException("Error animal type: " + type);
        }
    }

    private boolean isValidAnimalType(String type) {
        return !type.equals("Cat") && !type.equals("Dog") && !type.equals("Shark") && !type.equals("Wolf");
    }

    public Map<String, List<Animal>> createAnimals(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number of animals should be positive");
        }
        Map<String, List<Animal>> animalsMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String type = getRandomAnimalType();
            if (isValidAnimalType(type)) {
                throw new InvalidAnimalException("Invalid animal type: " + type);
            }
            Animal animalFactory = getRandomAnimalFactory(type);
            Animal animal = animalFactory.createAnimal("_" + i,
                    getRandomName(type),
                    BigDecimal.valueOf(random.nextDouble() * N),
                    getRandomCharacter(),
                    randBirthDate());

            animalsMap.computeIfAbsent(type, k -> new ArrayList<>()).add(animal);
        }

        return animalsMap;
    }

    public Map<String, List<Animal>> createAnimals() {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        int iterations = 0;

        while (uniqueAnimals.size() < 10 && iterations < 100) {
            String type = getRandomAnimalType();
            Animal animalFactory = getRandomAnimalFactory(type);
            AbstractAnimal animal = animalFactory.createAnimal("_" + uniqueAnimals.size(),
                    getRandomName(type),
                    BigDecimal.valueOf(random.nextDouble() * N),
                    getRandomCharacter(),
                    randBirthDate());

            if (uniqueAnimals.add(animal.getBreed())) {
                animalsMap.computeIfAbsent(type, k -> new ArrayList<>()).add(animal);
            } else {
                System.out.println("Not added: " + animal.getBreed());
            }

            iterations++;
        }

        return animalsMap;
    }

    public void printAnimalDetails(AbstractAnimal animal) {
        if (animal == null) {
            throw new InvalidAnimalException("Animal cannot be null");
        }
        System.out.println(animal.getBreed() + " " + animal.getName() + " " +
                animal.getCharacter() + " " + animal.getCost() + " " + animal.getBirthDate());
    }
}
