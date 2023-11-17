package edu.project3.Printer;

import edu.project3.model.Statistic;
import java.time.OffsetDateTime;
import java.util.Map;

public class AdocPrinter implements Printer {


    @Override
    public <T> String print(Statistic<T> statistic) {
        StringBuilder sb = new StringBuilder();
        sb.append("= ").append(statistic.title()).append("\n");
        sb.append("[cols=2]").append("\n").append("|====").append("\n");

        if (statistic.statistic() instanceof Map) {
            sb.append("|").append(statistic.keyName()).append(" |").append(statistic.valueName()).append("\n\n");
            for (Map.Entry<String, Integer> ent : ((Map<String, Integer>) statistic.statistic()).entrySet()) {
                sb.append("|").append(ent.getKey()).append("\n").append("|").append(ent.getValue().toString()).append("\n\n");
            }
        }
        if (statistic.statistic() instanceof Double
            || statistic.statistic() instanceof Integer
            || statistic.statistic() instanceof OffsetDateTime) {
            sb.append("|").append("Metric").append(" |").append(statistic.valueName()).append("\n\n");
            sb.append("|").append(statistic.keyName()).append("\n").append("|").append(statistic.statistic()).append("\n");
        }
        sb.append("|====");
        return sb.toString();
    }
}
