package c24.thriftshop.stripe.demo.util;

import java.time.Duration;
import java.time.Instant;

public class TestTimer {
    Duration duration;
    Instant start;
    Instant stop;

    public void start() {
        start = Instant.now();
    }

    public void stop() {
        stop = Instant.now();
        duration = Duration.between(start, stop);
    }

    public long getTimeInSeconds() {
        return duration.getSeconds();
    }

    public long getTimeInMilliSeconds() {
        return duration.getNano() / 1000000;
    }

    public long getTimeInNanoSeconds() {
        return duration.getNano();
    }
}
