package edu.hw3.Task8;

import java.util.*;

public class BackwardIterator<T> implements Iterator<T> {

    private T[] collectionArray;
    private int size;
    private int position;

    public BackwardIterator(Collection<T> collection) {
        size = collection.size();
        position = size - 1;
        collectionArray = (T[]) collection.toArray();
        System.out.println(Arrays.toString(collectionArray));
    }

    @Override
    public boolean hasNext() {
        return position >= 0;
    }

    @Override
    public T next() {
        return collectionArray[position--];

    }

    public static void main(String[] args) {
        BackwardIterator<Integer> bi = new BackwardIterator<>(Set.of(1,2,3));
        BackwardIterator<Integer> bi1 = new BackwardIterator<>(List.of(1,2,3));
        System.out.println(bi.next());
        System.out.println(bi.next());
        System.out.println(bi.next());
    }
}
