package ru.mtsbank.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.description.AbstractAnimal;
import ru.mtsbank.description.Animal;
import ru.mtsbank.search.AnimalsRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
    private final AnimalsRepository animalsRepository;

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    public ScheduledTasks(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @PostConstruct
    public void init() {
        startPrintDuplicateThread();
        startFindAverageAgeThread();
    }

    @Async
    public void startPrintDuplicateThread() {
        executorService.scheduleAtFixedRate(() -> {
            Thread.currentThread().setName("PrintDuplicateThread");
            System.out.println("\nDuplicate animals: ");
            Map<String, List<Animal>> duplicateAnimals = (Map<String, List<Animal>>) animalsRepository.findDuplicate();
            duplicateAnimals.forEach((type, count) -> System.out.println(type + ": " + count));
        }, 0, 20, TimeUnit.SECONDS);
    }

    @Async
    public void startFindAverageAgeThread() {
        executorService.scheduleAtFixedRate(() -> {
            Thread.currentThread().setName("FindAverageAgeThread");
            System.out.println("\nAverage age of all animals: ");
            System.out.println(animalsRepository.findAverageAge());
        }, 0, 10, TimeUnit.SECONDS);
    }

    @Async
    @Scheduled(fixedRate = 60000)
    public void printAnimals() {
        try {
            Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();
            Map<AbstractAnimal, Integer> olderAnimals = animalsRepository.findOlderAnimal(8);

            List<AbstractAnimal> oldAndExpensiveAnimals = animalsRepository.findOldAndExpensive();
            List<String> minCostAnimals = animalsRepository.findMinCostAnimals();

            System.out.println("\nCALL CALL CALL CALL CALL");

            System.out.println("\nLeap year names: ");
            leapYearNames.forEach((key, value) -> System.out.println(key + ": " + value));

            System.out.println("\nOlder animals: ");
            olderAnimals.forEach((animal, age) -> System.out.println(animal + ", age: " + age));

            System.out.println("\nOld and expensive animals: ");
            oldAndExpensiveAnimals.forEach(System.out::println);

            System.out.println("\nMinimum cost animals: ");
            minCostAnimals.forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("Error printAnimals(): " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}


