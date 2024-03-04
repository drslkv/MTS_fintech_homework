package ru.mtsbank.search;

import ru.mtsbank.description.AbstractAnimal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalsRepository {
    Map<String, LocalDate> findLeapYearNames();
    Map<AbstractAnimal, Integer> findOlderAnimal(int age);
    Map<String, List<AbstractAnimal>> findDuplicate();

    double findAverageAge();
    List<AbstractAnimal> findOldAndExpensive();
    List<String> findMinCostAnimals();

}
