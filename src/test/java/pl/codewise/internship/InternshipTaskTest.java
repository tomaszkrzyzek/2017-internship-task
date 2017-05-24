package pl.codewise.internship;

import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;

public class InternshipTaskTest {

    /*
        Niestety, nie wystarczyło mi czasu na napisanie sensownych testów
     */

    @Test
    public void shouldWork() {
        Scheduler scheduler = new Scheduler();
        scheduler.start();
        TimerId timerId = scheduler.regiester(Duration.ofSeconds(5), () -> {
            System.out.println("Callback");
        });

        try {
            scheduler.startTimer(timerId);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}