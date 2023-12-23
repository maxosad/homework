package edu.hw7.task4;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@SuppressWarnings("MagicNumber")
public class ParallelMonteCarlo {
    private AtomicInteger totalCount = new AtomicInteger(0);
    private AtomicInteger circleCount = new AtomicInteger(0);
    private final long time;
    private final double pi;
    List<Thread> threads;

    public ParallelMonteCarlo(int threadsCount, int experiments) {

        long startTime = System.currentTimeMillis();
        threads = new ArrayList<>(threadsCount);
        final int eachThreadIterations = experiments / 3;
        for (int threadIndex = 0; threadIndex < threadsCount; threadIndex++) {
            threads.add(getExecutorThread(eachThreadIterations));
        }

        for (int threadIndex = 0; threadIndex < threadsCount; threadIndex++) {
            threads.get(threadIndex).start();
        }
        try {
            for (int threadIndex = 0; threadIndex < threadsCount; threadIndex++) {

                threads.get(threadIndex).join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        pi = 4 * (1.0 * circleCount.intValue() / totalCount.intValue());
        time = System.currentTimeMillis() - startTime;
    }


    Consumer<Integer> consumer = iterations -> {
        SecureRandom rand = new SecureRandom();
        for (int experiment = 0; experiment < iterations; experiment++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            totalCount.incrementAndGet();
            if (Util.inCircle(x, y)) {
                circleCount.incrementAndGet();
            }
        }
    };

    private Thread getExecutorThread(int iterations) {
        return new Thread(() -> consumer.accept(iterations));
    }

    public long getTime() {
        return time;
    }

    public double getPi() {
        return pi;
    }

    public double getPiError() {
        return Math.abs(pi - Math.PI);
    }
}
