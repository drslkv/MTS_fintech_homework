package searchAnimal;

import descriptionAnimal.AbstractAnimal;

public interface SearchAnimalService {
    AbstractAnimal[] findLeapYearNames(AbstractAnimal[] animals);
    AbstractAnimal[] findOlderAnimal(AbstractAnimal[] animals, int age);
    void findDuplicate(AbstractAnimal[] animals);
}
