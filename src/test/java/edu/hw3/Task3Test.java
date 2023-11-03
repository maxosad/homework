package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

//freqDict(["a", "bb", "a", "bb"]) → {"bb": 2, "a": 2}
//    freqDict(["this", "and", "that", "and"]) → {"that": 1, "and": 2, "this": 1}
//    freqDict(["код", "код", "код", "bug"]) → {"код": 3, "bug": 1}
//    freqDict([1, 1, 2, 2]) → {1: 2, 2: 2}
class Task3Test {

    @Test
    @DisplayName("example test1")
    void freqDict1() {
        HashMap<String, Integer> testMap = new HashMap<>();
        testMap.put("bb", 2);
        testMap.put("a", 2);

        assertEquals(testMap, Task3.freqDict(new String[] {"a", "bb", "a", "bb"}));
    }
    @Test
    @DisplayName("example test2")
    void freqDict2() {
        HashMap<String, Integer> testMap = new HashMap<>();
        testMap.put("that", 1);
        testMap.put("and", 2);
        testMap.put("this", 1);

        assertEquals(testMap, Task3.freqDict(new String[] {"this", "and", "that", "and"}));
    }

    @Test
    @DisplayName("example test3")
    void freqDict3() {
        HashMap<String, Integer> testMap = new HashMap<>();
        testMap.put("код", 3);
        testMap.put("bug", 1);

        assertEquals(testMap, Task3.freqDict(new String[] {"код", "код", "код", "bug"}));
    }

    @Test
    @DisplayName("example test4")
    void freqDict4() {
        HashMap<Integer, Integer> testMap = new HashMap<>();
        testMap.put(1, 2);
        testMap.put(2, 2);

        assertEquals(testMap, Task3.freqDict(new Integer[] {1, 1, 2, 2}));
    }

}
