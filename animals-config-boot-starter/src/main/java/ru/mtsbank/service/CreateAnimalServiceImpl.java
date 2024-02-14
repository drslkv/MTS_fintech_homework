package ru.mtsbank.service;

import animals.Cat;
import animals.Dog;
import animals.Shark;
import animals.Wolf;
import descriptionAnimal.AbstractAnimal;
import descriptionAnimal.Animal;
import org.springframework.stereotype.Service;
import ru.mtsbank.config.AnimalProperties;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static ru.mtsbank.service.CreateAnimalService.*;

@Service
public class CreateAnimalServiceImpl {
    private final AnimalProperties animalProperties;

    public CreateAnimalServiceImpl(AnimalProperties animalProperties) {
        this.animalProperties = animalProperties;
    }

    public LocalDate randBirthDate() {
        LocalDate startDate = LocalDate.of(2010, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomEpochDay);
    }

    public String getRandomAnimalType() {
        String[] animalTypes = {"Cat", "Dog", "Shark", "Wolf"};
        return animalTypes[new Random().nextInt(animalTypes.length)];
    }

    public Animal getRandomAnimalFactory(String type) {
        int randomIndexName = random.nextInt(names.length);
        int randomIndexCharacter = random.nextInt(character.length);
        LocalDate birthDate = randBirthDate();

        switch (type) {
            case "Cat":
                return new Cat("Cat", names[randomIndexName],
                        BigDecimal.valueOf(random.nextDouble() * 1000), character[randomIndexCharacter],
                        birthDate);
            case "Dog":
                return new Dog("Dog", names[randomIndexName],
                        BigDecimal.valueOf(random.nextDouble() * 1000), character[randomIndexCharacter],
                        birthDate);
            case "Shark":
                return new Shark("Shark", names[randomIndexName],
                        BigDecimal.valueOf(random.nextDouble() * 1000), character[randomIndexCharacter],
                        birthDate);
            case "Wolf":
                return new Wolf("Wolf", names[randomIndexName],
                        BigDecimal.valueOf(random.nextDouble() * 1000), character[randomIndexCharacter],
                        birthDate);
            default:
                throw new IllegalArgumentException("Error animal type");
        }
    }

    public AbstractAnimal[] createAnimals(int n) {
        AbstractAnimal[] animals = new AbstractAnimal[n];

        for (int i = 0; i < n; i++) {
            String type = getRandomAnimalType();
            Animal animalFactory = getRandomAnimalFactory(type);
            String name = type.equals("Cat") ? animalProperties.getCatName() : animalProperties.getDogName();
            AbstractAnimal animal = animalFactory.createAnimal("_" + i,
                    name,
                    BigDecimal.valueOf(random.nextDouble() * 1000),
                    character[random.nextInt(character.length)],
                    randBirthDate());

            animals[i] = animal;
        }

        return animals;
    }

    public AbstractAnimal[] createAnimals() {
        int iterations = 0;
        List<AbstractAnimal> animalsList = new ArrayList<>();

        do {
            String type = getRandomAnimalType();
            Animal animalFactory = getRandomAnimalFactory(type);
            String name = type.equals("Cat") ? animalProperties.getCatName() : animalProperties.getDogName();
            AbstractAnimal animal = animalFactory.createAnimal("_" + uniqueAnimals.size(),
                    name,
                    BigDecimal.valueOf(random.nextDouble() * 1000),
                    character[random.nextInt(character.length)],
                    randBirthDate());

            if (uniqueAnimals.add(animal.getBreed())) {
                animalsList.add(animal);
            } else {
                System.out.println("Not added: " + animal.getBreed());
            }

            iterations++;
        } while (uniqueAnimals.size() < 10 && iterations < 100);

        return animalsList.toArray(new AbstractAnimal[0]);
    }

    public void printAnimalDetails(AbstractAnimal animal) {
        System.out.println(animal.getBreed() + " " + animal.getName() + " " +
                animal.getCharacter() + " " + animal.getCost() + " " + animal.getBirthDate());
    }
}
