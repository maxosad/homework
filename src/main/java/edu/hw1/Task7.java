package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;

public class Task7 {

    public static ArrayList<Integer> integerInBits(int number) {
        ArrayList<Integer> bits = new ArrayList<>();
        do {
            bits.add(number & 1);
            number/=2;
        } while(number!=0) ;

        Collections.reverse(bits);
        return bits;
    }

    public static int bitsInInteger(ArrayList<Integer> bits) {
        int ans = 0;
        for (var el : bits) {
            ans *= 2;
            ans += el;
        }

        return ans;
    }

    public static int rotateLeft(int n, int shift) {
        ArrayList<Integer> bits = integerInBits(n);
        ArrayList<Integer> firstPart = new ArrayList<> (bits.subList(0, shift));
        ArrayList<Integer> secondPart = new ArrayList<> (bits.subList(shift, bits.size()));
//        Collections.reverse(firstPart);

        secondPart.addAll(firstPart);
        
        return bitsInInteger(secondPart);
    }

    public static int rotateRight(int n, int shift) {
        ArrayList<Integer> bits = integerInBits(n);
        int size = bits.size();
        ArrayList<Integer> firstPart = new ArrayList<>(bits.subList(size-shift, size));
        ArrayList<Integer> secondPart = new ArrayList<>(bits.subList(0, size-shift));

//        Collections.reverse(firstPart);
        firstPart.addAll(secondPart);

        return bitsInInteger(firstPart);
    }

    public static void main(String[] args) {
        System.out.println(integerInBits(1));
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        ArrayList<Integer> a1 = (ArrayList<Integer>) arr.subList(0, 2);
        System.out.println(a1);
    }
}
