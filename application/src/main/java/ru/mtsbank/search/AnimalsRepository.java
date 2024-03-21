package ru.mtsbank.search;

import ru.mtsbank.description.AbstractAnimal;
import ru.mtsbank.description.Animal;
import ru.mtsbank.exception.InsufficientAnimalsException;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalsRepository {
    Map<String, LocalDate> findLeapYearNames();
    Map<AbstractAnimal, Integer> findOlderAnimal(int age);
    Map<String, List<Animal>> findDuplicate();

    double findAverageAge();
    List<AbstractAnimal> findOldAndExpensive();
    List<String> findMinCostAnimals() throws InsufficientAnimalsException;

}
