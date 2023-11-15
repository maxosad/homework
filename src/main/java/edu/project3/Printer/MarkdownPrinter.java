package edu.project3.Printer;

import edu.project3.model.Statistic;

public class MarkdownPrinter implements Printer {

    @Override
    public <T> String print(Statistic<T> statistic) {
        StringBuilder sb = new StringBuilder();
        sb.append("#####").append(statistic.title());
        sb.append(" ");
        sb.append(statistic.statistic());
        return sb.toString();
    }
}
