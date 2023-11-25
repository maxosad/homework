package edu.hw6.task1;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DiskMap extends AbstractMap<String, String> implements Map<String, String> {
    private final static String PATH = "src/main/java/edu/hw6/task1/Disk.txt";

    public DiskMap() {
        this.clear();
    }

    private void writeSet(Set<Entry<String, String>> entrySet) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
            for (var entry : entrySet) {
                oos.writeObject(entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> diskSet = new HashSet<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))) {
            boolean isFileNotEnded = true;
            while (isFileNotEnded) {
                try {
                    var o = ois.readObject();
                    AbstractMap.SimpleEntry<String, String> p =
                        (AbstractMap.SimpleEntry<String, String>) o;
                    diskSet.add(p);
                } catch (EOFException | ClassNotFoundException e) {
                    isFileNotEnded = false;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return diskSet;
    }

    @Override
    public String put(String key, String value) {
        var entrySet = entrySet();
        AbstractMap.SimpleEntry<String, String> newEntry = new AbstractMap.SimpleEntry<>(key, value);
        Entry<String, String> oldEntry = null;
        for (var entry : entrySet) {
            if (entry.getKey().equals(key)) {
                oldEntry = entry;
            }
        }
        if (oldEntry != null) {
            entrySet.remove(oldEntry);
        }
        entrySet.add(newEntry);
        writeSet(entrySet);
        return value;
    }

    @Override
    public String remove(Object key) {
        String ansString = null;
        var entrySet = entrySet();
        for (var entry : entrySet) {
            if (entry.getKey().equals(key)) {
                ansString = entry.getValue();
            }
        }
        if (ansString != null) {
            entrySet.remove(new SimpleEntry<>((String) key, ansString));
            writeSet(entrySet);
        }
        return ansString;
    }


    @Override
    public void clear() {
        writeSet(new HashSet<>());
    }
}

