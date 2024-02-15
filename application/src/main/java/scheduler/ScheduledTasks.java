package scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledTasks {
    @Scheduled(cron = "*/3 * * * * *") // вызов метода каждую минуту
    public void printAnimals() {
        log.info("Hello");
    }
}


