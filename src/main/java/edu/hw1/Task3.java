package edu.hw1;

public class Task3 {

    public static Pair minMax(Integer[] arr) {
        Integer min = null;
        Integer max = null;

        for (Integer el : arr) {
            if (min == null || min > el) {
                min = el;
            }
            if (max == null || max < el) {
                max = el;
            }
        }
        return new Pair(min, max);
    }

    public static Boolean isNestable(Integer[] arr1, Integer[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) {
            return false;
        }
        Pair minMaxArr1 = minMax(arr1);
        Pair minMaxArr2 = minMax(arr2);

        return  (minMaxArr1.getMin() > minMaxArr2.getMin()) || (minMaxArr1.getMax() < minMaxArr2.getMax());
    }

    static class Pair {
        private Integer min;
        private Integer max;

        Pair(Integer min, Integer max) {
            this.min = min;
            this.max = max;
        }

        public Integer getMin() {
            return min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

        public Integer getMax() {
            return max;
        }

        public void setMax(Integer max) {
            this.max = max;
        }
    }
}
