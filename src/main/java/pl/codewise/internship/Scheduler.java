package pl.codewise.internship;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tomaszkrzyzek on 24/05/2017.
 */
public class Scheduler extends Thread{

    private Map<TimerId, Runnable> timers = new HashMap<>();
    private Duration resolution;

    //default resolution is 1 sec
    public Scheduler() {
        resolution = Duration.ofSeconds(1);
    }

    public Scheduler(Duration resolution) {
        this.resolution = resolution;
    }

    public TimerId regiester(Duration timeout, Runnable callback){
        TimerId timerId = new TimerId(timeout);
        timers.put(timerId, callback);
        return timerId;
    }

    public void startTimer(TimerId timerId){
        timerId.start();
    }

    public void stopTimer(TimerId timerId){
        timerId.interrupt();
        timers.remove(timerId);
    }

    @Override
    public void run() {
        try {
            while(!this.isInterrupted()) {
                for (Map.Entry<TimerId, Runnable> entry : timers.entrySet()) {
                    Duration timeout = entry.getKey().getTimeout();
                    if (timeout.isZero() || timeout.isNegative()) {
                        new Thread(entry.getValue()).start();
                        timers.remove(entry);
                    }
                }
                Thread.sleep(resolution.toMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}