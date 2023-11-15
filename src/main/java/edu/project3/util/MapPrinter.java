package edu.project3.util;

import java.util.Map;

public class MapPrinter {
    public <K, V> String print(Map<K,V> m) {
        StringBuilder sb = new StringBuilder();
        for (var entry : m.entrySet()) {
            sb.append("#####").append(entry.getKey()).append(" | ").append(entry.getValue());
        }
        return sb.toString();
    }
}
