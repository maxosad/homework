package edu.hw8.task2;

import org.apache.logging.log4j.util.Supplier;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public class FixedThreadPool implements ThreadPool {
    private final Thread[] threads;
    private final LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>(10);
    private final int nThreads;
    private final Runnable executor = () -> {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    };

    private FixedThreadPool(int nThreads) {
        this.nThreads = nThreads;
        this.threads = new Thread[nThreads];
        for (int threadIndex = 0; threadIndex < nThreads; threadIndex++) {
            threads[threadIndex] = new Thread(executor);
        }
    }

    public static FixedThreadPool create(int nThreads) {
        return new FixedThreadPool(nThreads);
    }

    @Override
    public void start() {
        Arrays.stream(threads).forEach(Thread::start);
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        Arrays.stream(threads).forEach(Thread::interrupt);
    }
}
