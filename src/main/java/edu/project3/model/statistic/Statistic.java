package edu.project3.model.statistic;

public abstract class Statistic<T> {
    private final T statistic;

    protected Statistic(T statistic) {
        this.statistic = statistic;
    }

    public T getStatistic() {
        return statistic;
    }
}
