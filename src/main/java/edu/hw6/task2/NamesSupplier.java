package edu.hw6.task2;

import java.nio.file.Path;
import java.util.function.Supplier;

public class NamesSupplier implements Supplier<Path> {
    private Integer counter = 0;
    public static final Integer ONE = 1;
    private final String initialName;
    private final String nameStart;
    private final String nameEnd;
    public final static String COPY = "Копия";

    public NamesSupplier(Path initialPath) {
        this.initialName = initialPath.getFileName().toString();
        String[] nameSplited = initialName.split("\\.");
        Path parent = initialPath.getParent();
        nameStart =  parent + (parent.equals(Path.of("")) ? "" : "\\") + nameSplited[0];
        nameEnd = "." + nameSplited[1];
    }

    @Override
    public Path get() {
        counter++;
        return Path.of(nameStart + " — копия" + (counter.equals(ONE) ? "" : " (" + counter + ")") + nameEnd);
    }
}
