package edu.hw7;

import lombok.SneakyThrows;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    private AtomicInteger sharedValue = new AtomicInteger(0);

    @SneakyThrows
    public int parallelIncrement(int incrementAmount) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < incrementAmount; i++) {
                sharedValue.incrementAndGet();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < incrementAmount; i++) {
                sharedValue.incrementAndGet();
            }
        });
        thread1.start();
        thread2.start();
        
        thread1.join();
        thread2.join();

        return sharedValue.intValue();
    }

    public int getSharedValue() {
        return sharedValue.intValue();
    }
}
