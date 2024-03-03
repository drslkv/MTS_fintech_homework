package ru.mtsbank.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.descriptionAnimal.AbstractAnimal;
import ru.mtsbank.searchAnimal.AnimalsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ScheduledTasks {
    private final AnimalsRepository animalsRepository;

    public ScheduledTasks(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }
    @Scheduled(fixedRate = 60000)
    public void printAnimals() {
        Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();
        Map<AbstractAnimal, Integer> olderAnimals = animalsRepository.findOlderAnimal(5);
        Map<String, Integer> duplicateAnimals = animalsRepository.findDuplicate();

        System.out.println("\nCALL CALL CALL CALL CALL");

        System.out.println("\nLeap year names: ");
        leapYearNames.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("\nOlder animals: ");
        olderAnimals.forEach((animal, age) -> System.out.println(animal + ", age: " + age));

        System.out.println("\nDuplicate animals: ");
        duplicateAnimals.forEach((type, count) -> System.out.println(type + ": " + count));
    }
}


