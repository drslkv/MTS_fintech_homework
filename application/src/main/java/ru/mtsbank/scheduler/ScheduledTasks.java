package ru.mtsbank.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.descriptionAnimal.AbstractAnimal;
import ru.mtsbank.searchAnimal.AnimalsRepository;

import java.util.List;

@Slf4j
@Component
public class ScheduledTasks {
    private final AnimalsRepository animalsRepository;

    public ScheduledTasks(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }
    @Scheduled(fixedRate = 60000)
    public void printAnimals() {
        List<AbstractAnimal> leapYearNames = animalsRepository.findLeapYearNames();
        List<AbstractAnimal> olderAnimals = animalsRepository.findOlderAnimal(5); // Пример возраста
        animalsRepository.printDuplicate();

        System.out.println("\nCALL CALL CALL CALL CALL");

        System.out.println("\nLeap year names: ");
        for (AbstractAnimal animal : leapYearNames) {
            System.out.println(animal);
        }

        System.out.println("\nOlder animals: ");
        for (AbstractAnimal animal : olderAnimals) {
            System.out.println(animal);
        }
    }
}


