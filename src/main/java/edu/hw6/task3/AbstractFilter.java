package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

@FunctionalInterface
public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    default AbstractFilter and(AbstractFilter abstractFilter) {
        return (t) -> this.accept(t) && abstractFilter.accept(t);
    }
}
