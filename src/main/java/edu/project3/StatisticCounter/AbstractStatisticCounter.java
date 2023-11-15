package edu.project3.StatisticCounter;

import java.util.List;
import java.util.function.Function;

public class AbstractStatisticCounter<T,K,V> implements StatisticCounter<T>{
    private Function<K, V> f;
    private String title;
    public AbstractStatisticCounter(String title, Function<K, V> f) {
        this.f = f;
        this.title = title;
    }
    @Override
    public V countStatistic(List<T> list) {
        return f.apply(list);
    }
}
