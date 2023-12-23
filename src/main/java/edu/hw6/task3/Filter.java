package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

@FunctionalInterface
public interface Filter extends DirectoryStream.Filter<Path> {
    default Filter and(Filter filter) {
        return (t) -> this.accept(t) && filter.accept(t);
    }
}
