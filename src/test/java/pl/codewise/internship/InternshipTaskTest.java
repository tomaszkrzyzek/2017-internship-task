package pl.codewise.internship;

import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;

public class InternshipTaskTest {

    @Test
    public void shouldWork() {
        Scheduler scheduler = new Scheduler();
        scheduler.start();
        TimerId timerId = scheduler.regiester(Duration.ofSeconds(5), () -> {
            System.out.println("Callback");
        });

        scheduler.startTimer(timerId);

    }
}