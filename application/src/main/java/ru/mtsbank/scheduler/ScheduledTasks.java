package ru.mtsbank.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.description.AbstractAnimal;
import ru.mtsbank.description.Animal;
import ru.mtsbank.search.AnimalsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class ScheduledTasks {
    private final AnimalsRepository animalsRepository;

    public ScheduledTasks(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }
    @Scheduled(fixedRate = 60000)
    public void printAnimals() {
        try {
            Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();
            Map<AbstractAnimal, Integer> olderAnimals = animalsRepository.findOlderAnimal(8);
            Map<String, List<Animal>> duplicateAnimals = animalsRepository.findDuplicate();

            double averageAge = animalsRepository.findAverageAge();
            List<AbstractAnimal> oldAndExpensiveAnimals = animalsRepository.findOldAndExpensive();
            List<String> minCostAnimals = animalsRepository.findMinCostAnimals();

            System.out.println("\nCALL CALL CALL CALL CALL");

            System.out.println("\nLeap year names: ");
            leapYearNames.forEach((key, value) -> System.out.println(key + ": " + value));

            System.out.println("\nOlder animals: ");
            olderAnimals.forEach((animal, age) -> System.out.println(animal + ", age: " + age));

            System.out.println("\nDuplicate animals: ");
            duplicateAnimals.forEach((type, count) -> System.out.println(type + ": " + count));

            System.out.println("\nAverage age of all animals: " + averageAge);

            System.out.println("\nOld and expensive animals: ");
            oldAndExpensiveAnimals.forEach(System.out::println);

            System.out.println("\nMinimum cost animals: ");
            minCostAnimals.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error printAnimals(): " + e.getMessage());
            e.printStackTrace();
        }
    }
}


