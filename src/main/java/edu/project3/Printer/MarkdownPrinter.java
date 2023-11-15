package edu.project3.Printer;

import edu.project3.model.statistic.Statistic;
import jdk.jshell.spi.ExecutionControl;

public class MarkdownPrinter implements Printer {
    @Override
    public void print(Statistic statistic) {
        throw new RuntimeException(new ExecutionControl.NotImplementedException(""));
    }
}
