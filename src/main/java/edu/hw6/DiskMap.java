package edu.hw6;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DiskMap extends AbstractMap<String, String> implements Map<String, String> {
    private final static String PATH = "src/main/java/edu/hw6/Disk.txt";

    public static void main(String[] args) {
        HashMap<String, String> m = new HashMap<>();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
            AbstractMap.SimpleEntry<String, String> entry = new SimpleEntry<>("maxim", "osadchiy");
            oos.writeObject(entry);
            AbstractMap.SimpleEntry<String, String> entry1 = new SimpleEntry<>("maxim1", "osadchiy2");
            oos.writeObject(entry1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DiskMap mm = new DiskMap();
        System.out.println(mm.entrySet());
    }
    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> diskSet = new HashSet<>();
//        Set<Entry<String, String>> diskSet = new EntrySet<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))) {
            boolean f = true;
            while (f) {
                try {
                    AbstractMap.SimpleEntry<String, String> p =
                        (AbstractMap.SimpleEntry<String, String>) ois.readObject();
                    diskSet.add(p);
                } catch (EOFException ignore) {
                    f = false;
                }

            }
//            System.out.println(p.toString());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return diskSet;
    }

    @Override
    public String put(String key, String value) {
        var entrySet = this.entrySet();
        AbstractMap.SimpleEntry<String, String> newEntry = new AbstractMap.SimpleEntry<>(key, value);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
            boolean existedKey = true;
            for (var entry : entrySet) {
                if (entry.getKey().equals(key)) {
                    oos.writeObject(newEntry);
                    existedKey = false;
                } else {
                    oos.writeObject(entry);
                }
            }
            if (existedKey) {
                oos.writeObject(newEntry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
}

