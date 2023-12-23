package edu.hw8.task3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.bind.DatatypeConverter;

@SuppressWarnings("MagicNumber")
public class MultithreadingPasswordChecker extends AbstractChecker  {
    private AtomicInteger counter;

    public MultithreadingPasswordChecker(int passToGuess) {
        counter = new AtomicInteger(passToGuess);
    }

    private final Runnable task = () -> {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String passString = passwordSupplier.get();
        md.update(passString.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        String hashPassword = DatatypeConverter.printHexBinary(digest).toLowerCase();
        String name = hashName.get(hashPassword);
        if (name != null && !namePassword.containsKey(name)) {
            namePassword.put(name, passString);
            counter.decrementAndGet();
        }
    };

    public void start() {
        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {
            while (true) {
                if (counter.get() == 0) {
                    executor.shutdownNow();
                    break;
                }
                executor.execute(task);
            }
        }
    }
}
