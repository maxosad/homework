package edu.project2;

import java.util.Arrays;

public class prov {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3};
        int[] b = new int[3];
        System.arraycopy(a, 0, b, 0, 2);
        // с текущей позиции length элементов
        System.out.println(Arrays.toString(b));
    }
}
