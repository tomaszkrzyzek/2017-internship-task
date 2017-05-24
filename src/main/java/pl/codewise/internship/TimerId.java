package pl.codewise.internship;

import java.time.Duration;

/**
 * Created by tomaszkrzyzek on 24/05/2017.
 */
public class TimerId extends Thread{

    private Duration timeout;
    private Duration resolution;

    //default resolution is 1 sec
    public TimerId(Duration timeout) {
        this.timeout = timeout;
        this.resolution = Duration.ofSeconds(1);
    }

    public TimerId(Duration timeout, Duration resolution) {
        this.timeout = timeout;
        this.resolution = resolution;
    }

    public Duration getTimeout() {
        return timeout;
    }

    @Override
    public void run() {
            try {
                while (!isInterrupted()) {
                    while(true) {
                        timeout = timeout.minus(resolution);
                        Thread.sleep(resolution.toMillis());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}