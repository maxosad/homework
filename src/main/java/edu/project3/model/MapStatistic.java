package edu.project3.model;

import java.util.Map;

public record MapStatistic<K, V>(String title, Map<K,V> statistic) { }
