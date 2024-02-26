package ru.mtsbank.searchAnimal;

import ru.mtsbank.descriptionAnimal.AbstractAnimal;

import java.time.LocalDate;
import java.util.Map;

public interface AnimalsRepository {
    Map<String, LocalDate> findLeapYearNames();
    Map<AbstractAnimal, Integer> findOlderAnimal(int age);
    Map<String, Integer> findDuplicate();
}
