package edu.hw9.task1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Metric {
    private final Lock sumLock = new ReentrantLock();
    private final Lock minLock = new ReentrantLock();
    private final Lock maxLock = new ReentrantLock();
    private final Lock countLock = new ReentrantLock();
    private String name;
    private double sum;
    private double count;
    private double max;
    private double min;

    public Metric(String name, double sum, double count, double max, double min) {
        this.name = name;
        this.sum = sum;
        this.count = count;
        this.max = max;
        this.min = min;
    }

    public void addSum(double sum) {
        if (sumLock.tryLock()){
            try {
                this.sum += sum;
            } finally {
                sumLock.unlock();
            }
        }
    }

    public void addMin(double min) {
        if (minLock.tryLock()){
            try {
                if (this.min > min) {
                    this.min = min;
                }
            } finally {
                minLock.unlock();
            }
        }
    }

    public void addMax(double max) {
        if (maxLock.tryLock()){
            try {
                if (this.max < max) {
                    this.max = max;
                }
            } finally {
                maxLock.unlock();
            }
        }
    }

    public void addCount(double count) {
        if (countLock.tryLock()){
            try {
                this.count += count;
            } finally {
                countLock.unlock();
            }
        }
    }

    public String getName() {
        return name;
    }

    public double getSum() {
        return sum;
    }

    public double getCount() {
        return count;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getAverage() {
        if (count == 0) {
            throw new RuntimeException("Divided by zero");
        }
        return sum / count;
    }
}
