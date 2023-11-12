package edu.hw6.task1;

import java.io.*;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DiskMap extends AbstractMap<String, String> implements Map<String, String> {
    private final static String PATH = "src/main/java/edu/hw6/task1/Disk.txt";
    public DiskMap() {
        this.clear();
    }

    public static void main(String[] args) {
        DiskMap m = new DiskMap();
        m.put("2", "2");
        m.put("2", "q");
        System.out.println(m.entrySet());
        System.out.println(m.size());
    }

    private void writeSet(Set<Entry<String, String>> entrySet) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
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
//        Set<Entry<String, String>> diskSet = new EntrySet<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))) {
            boolean f = true;
            while (f) {
                try {
                    var o = ois.readObject();
                    AbstractMap.SimpleEntry<String, String> p =
                        (AbstractMap.SimpleEntry<String, String>) o;
                    diskSet.add(p);
                } catch (EOFException | ClassNotFoundException e) {
                    f = false;
                }
            }
//            System.out.println(p.toString());
        } catch (FileNotFoundException ignore) {
//            throw new RuntimeException(e);
        } catch (IOException ignore) {
//            throw new RuntimeException(e);
        }
        return diskSet;
    }

//    @Override
//    public int size() {
//        return entrySet().size();
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return size() == 0;
//    }
//
//    @Override
//    public boolean containsKey(Object key) {
//        return false;
//    }
//
//    @Override
//    public boolean containsValue(Object value) {
//        return false;
//    }
//
//    @Override
//    public String get(Object key) {
//        return null;
//    }

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

//    @Override
//    public void putAll(Map<? extends String, ? extends String> m) {
//
//    }

    @Override
    public void clear() {
        writeSet(new HashSet<>());
    }

//    @Override
//    public Set<String> keySet() {
//        return null;
//    }

//    @Override
//    public Collection<String> values() {
//        return null;
//    }
}

