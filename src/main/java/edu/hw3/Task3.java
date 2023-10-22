package edu.hw3;

import java.util.HashMap;
import java.util.Map;

public class Task3 {

    public static <K> Map<K, Integer> freqDict(K[] arrayOfKeys) {
        Map<K, Integer> ansMap = new HashMap<>();
        for(K el : arrayOfKeys) {
            ansMap.put(el, ansMap.getOrDefault(el, 0)+1);
        }
        return ansMap;
    }
}
