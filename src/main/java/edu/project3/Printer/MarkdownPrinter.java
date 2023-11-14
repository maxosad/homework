package edu.project3.Printer;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import jdk.jshell.spi.ExecutionControl;
import java.util.List;

public class MarkdownPrinter implements Printer {
    @Override
    public void print(Statistic statistic) {
        throw new RuntimeException(new ExecutionControl.NotImplementedException(""));
    }
}
