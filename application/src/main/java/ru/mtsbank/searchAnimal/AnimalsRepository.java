package ru.mtsbank.searchAnimal;

import ru.mtsbank.descriptionAnimal.AbstractAnimal;

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
