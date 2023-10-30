package edu.hw3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Task3 {
    private Task3() { }

    public static <K> Map<K, Integer> freqDict(K[] arrayOfKeys) {
        Map<K, Integer> ansMap = new HashMap<>();
        for (K el : arrayOfKeys) {
            ansMap.merge(el, 1, Integer::sum);
        }
        return ansMap;
    }
}
