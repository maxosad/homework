package edu.hw8.task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class AbstractChecker implements PasswordChecker {

    protected static final MessageDigest MD;

    static {
        try {
            MD = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    protected final Map<String, String> hashName = new HashMap<>();
    protected final Map<String, String> namePassword = new HashMap<>();
    protected final Supplier<String> passwordSupplier = new PasswordSupplier(4);

    public Map<String, String> getNamePassword() {
        return new HashMap<>(namePassword);
    }

    public void readHashNameFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            String[] nameHash = line.split(" ");
            hashName.put(nameHash[1], nameHash[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String nextPassword() {
        return passwordSupplier.get();
    }
}
