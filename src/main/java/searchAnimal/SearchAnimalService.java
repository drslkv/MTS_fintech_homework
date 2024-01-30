package searchAnimal;

import descriptionAnimal.AbstractAnimal;

import java.util.List;

public interface SearchAnimalService {
    AbstractAnimal[] findLeapYearNames(AbstractAnimal[] animals);
    AbstractAnimal[] findOlderAnimal(AbstractAnimal[] animals, int age);
    List<String> findDuplicate(AbstractAnimal[] animals);
    void printDuplicate(AbstractAnimal[] animals);
}
