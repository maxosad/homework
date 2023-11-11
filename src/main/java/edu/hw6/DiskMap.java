package edu.hw6;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DiskMap extends AbstractMap<String, String> implements Map<String, String> {
    private final static String PATH = "src/main/java/edu/hw6/Disk.txt";
//    private final static String PATH = "Disk.txt";

    public static void main(String[] args) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH)))
        {
            AbstractMap.SimpleEntry<String, String> entry = new SimpleEntry<>("maxim", "osadchiy");
            oos.writeObject(entry);
            AbstractMap.SimpleEntry<String, String> entry1 = new SimpleEntry<>("maxim1", "osadchiy2");
            oos.writeObject(entry1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DiskMap m = new DiskMap();
        System.out.println(m.entrySet());


//        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH)))
//        {
//            AbstractMap.SimpleEntry<String, String> p = (AbstractMap.SimpleEntry<String, String>)ois.readObject();
//            AbstractMap.SimpleEntry<String, String> p1 = (AbstractMap.SimpleEntry<String, String>)ois.readObject();
//            System.out.println(p.toString());
//            System.out.println(p1.toString());
//            System.out.println(ois.readObject()==EOF);
////            System.out.println(p2.toString());
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> diskSet = new HashSet<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))) {
            while (true) {
                try {
                    var o = ois.readObject();
                    AbstractMap.SimpleEntry<String, String> p =
                        (AbstractMap.SimpleEntry<String, String>) o;
                    diskSet.add(p);
                }catch (EOFException ignore) { }

            }
//            System.out.println(p.toString());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return diskSet;
    }

    @Override
    public String put(String key, String value) {
        return null;
    }
}
