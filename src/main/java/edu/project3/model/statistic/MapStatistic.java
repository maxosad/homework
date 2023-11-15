package edu.project3.model.statistic;

import edu.project3.model.statistic.Statistic;
import java.util.Map;

public class MapStatistic<K, V> extends Statistic<Map<K, V>> {
    protected MapStatistic(Map<K, V> statistic) {
        super(statistic);
    }
}
