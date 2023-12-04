package edu.hw8.task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import javax.xml.bind.DatatypeConverter;

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

    protected Map<String, String> getNamePassword() {
        return new HashMap<>(namePassword);
    }

    protected Map<String, String> getHashName() {
        return new HashMap<>(hashName);
    }



    public void readHashNameFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            while (line != null) {
                String[] nameHash = line.split(" ");
                hashName.put(nameHash[1], nameHash[0]);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String nextPassword() {
        return passwordSupplier.get();
    }

    protected String countMD5Hash(String stringToHash) {
        MD.update(stringToHash.getBytes(StandardCharsets.UTF_8));
        byte[] digest = MD.digest();
        return DatatypeConverter.printHexBinary(digest).toLowerCase();
    }
}
