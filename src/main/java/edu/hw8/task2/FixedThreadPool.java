package edu.hw8.task2;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FixedThreadPool implements ThreadPool {
    private static final Lock CLOSE_LOCK = new ReentrantLock();
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
    private boolean isClosed;

    private FixedThreadPool(int nThreads) {
        isClosed = false;
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
        if (isClosed) {
            throw new RuntimeException("Pool already closed");
        }
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        isClosed = true;
        if (CLOSE_LOCK.tryLock()) {
            Arrays.stream(threads).forEach(Thread::interrupt);
            Arrays.stream(threads).forEach(t -> {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            CLOSE_LOCK.unlock();
        }
    }
}
