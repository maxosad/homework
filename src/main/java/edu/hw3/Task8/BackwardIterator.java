package edu.hw3.Task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {

    private T[] collectionArray;
    private int size;
    private int position;

    public BackwardIterator(Collection<T> collection) {
        size = collection.size();
        position = size - 1;
        collectionArray = (T[]) collection.toArray();
    }

    @Override
    public boolean hasNext() {
        return position >= 0;
    }

    @Override
    public T next() {
        if (hasNext()) {
            return collectionArray[position--];
        } else {
            throw new NoSuchElementException();
        }

    }

}
