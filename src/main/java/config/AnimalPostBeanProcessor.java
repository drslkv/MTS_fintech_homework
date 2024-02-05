package config;

import createAnimal.CreateAnimalService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class AnimalPostBeanProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CreateAnimalService) {
            initializeAnimalType((CreateAnimalService) bean);
        }
        return bean;
    }

    private void initializeAnimalType(CreateAnimalService createAnimalService) {
        String animalType = createAnimalService.getRandomAnimalType();
        createAnimalService.setAnimalType(animalType);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
