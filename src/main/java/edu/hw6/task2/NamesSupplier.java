package edu.hw6.task2;

import java.nio.file.Path;
import java.util.function.Supplier;

public class NamesSupplier implements Supplier<Path> {
    public static final int ONE = 1;
    private int counter = 0;
    private final String nameStart;
    private final String nameEnd;

    public NamesSupplier(Path initialPath) {
        String initialName = initialPath.getFileName().toString();
        String[] nameSplited = initialName.split("\\.");
        Path parent = initialPath.getParent();
        nameStart =  parent + (parent.equals(Path.of("")) ? "" : "\\") + nameSplited[0];
        nameEnd = "." + nameSplited[1];
    }

    @Override
    public Path get() {
        counter++;
        return Path.of(nameStart + " — копия" + (counter == ONE ? "" : " (" + counter + ")") + nameEnd);
    }
}
