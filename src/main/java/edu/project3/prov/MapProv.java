package edu.project3.prov;

import java.util.HashMap;
import java.util.Map;

public class MapProv {
    public static void main(String[] args) {
        Map<String, Integer> m = new HashMap<>();
        m.merge("1", 1, Integer::sum);
        m.merge("1", 1, Integer::sum);
        System.out.println(m.get("1"));
    }
}
