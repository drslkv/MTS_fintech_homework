package searchAnimal;

import descriptionAnimal.AbstractAnimal;
import java.util.List;
import java.util.Set;

public interface AnimalsRepository {
    List<AbstractAnimal> findLeapYearNames();
    List<AbstractAnimal> findOlderAnimal(int age);
    Set<String> findDuplicate();
    void printDuplicate();
    void addAll(List<AbstractAnimal> animals);
    public void addAnimals(List<AbstractAnimal> animals);
}
