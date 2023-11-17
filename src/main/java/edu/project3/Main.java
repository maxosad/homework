package edu.project3;

import edu.project3.Parser.Parser;
import edu.project3.Parser.ParserImpl;
import edu.project3.Printer.BridgePrinter;
import edu.project3.Printer.Printer;
import edu.project3.StatisticCounter.AdditionalStat.HttpUserAgentStatisticCounter;
import edu.project3.StatisticCounter.AdditionalStat.UserStatCounter;
import edu.project3.StatisticCounter.AvgBodyBytes;
import edu.project3.StatisticCounter.CountRequests;
import edu.project3.StatisticCounter.EarlyDate;
import edu.project3.StatisticCounter.LateDate;
import edu.project3.StatisticCounter.MostFrequentCode;
import edu.project3.StatisticCounter.MostFrequentResource;
import edu.project3.model.LogRecord;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
//    public static String DEFAULT_FORMAT_STRING = "markdown";
    public static String DEFAULT_FORMAT_STRING = "adoc";

    private Main() { }
    public static Set<String> KEYS = new HashSet<>(List.of("--path", "--from", "--to", "--format"));
    public static final Logger LOGGER = LogManager.getLogger();

    /**
     scenario 1
     --path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs
     scenario 2
     --path src/main/java/edu/project3/data
     */

    private static void logOutput(String s) {
        LOGGER.info("\n" + s);
    }
    public static void main(String[] args) {
        String path = null;
        OffsetDateTime fromDate = null ;
        OffsetDateTime toDate = null;
        String format = DEFAULT_FORMAT_STRING;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int argsLength = args.length;
        for (int i = 0; i < argsLength; i += 2) {
            if (KEYS.contains(args[i])) {
                switch (args[i]) {
                    case "--path" -> path = args[i + 1];
                    case "--from" -> fromDate = OffsetDateTime.parse(args[i + 1], formatter);
                    case "--to" -> toDate  = OffsetDateTime.parse(args[i + 1], formatter);
                    case "--format" -> format = args[i + 1];
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        Parser parser = new ParserImpl();
        List<LogRecord> logRecords = parser.parse(path);
        System.out.println(logRecords);

        if (fromDate != null) {
            OffsetDateTime finalFromDate = fromDate;
            logRecords = logRecords.stream()
                .filter(x -> x.date().isAfter(finalFromDate))
                .toList();
        }

        if (toDate != null) {
            OffsetDateTime finalToDate = toDate;
            logRecords = logRecords.stream()
                .filter(x -> x.date().isBefore(finalToDate))
                .toList();
        }

//        System.out.println("------------------------");
//        System.out.println(logRecords.stream()
//            .map(LogRecord::date)
//            .max(Comparator.naturalOrder()).orElse(null)
//        );


        var statsCounters = List.of(
        new AvgBodyBytes(),
        new CountRequests(),
        new EarlyDate(),
        new LateDate(),
        new MostFrequentCode(),
        new MostFrequentResource(),
        new HttpUserAgentStatisticCounter(),
        new UserStatCounter()
        );
//        System.out.println(new LateDate().countStatistic(logRecords).statistic());
//
        Printer printer = new BridgePrinter(format);
        List<LogRecord> finalLogRecords = logRecords;

        statsCounters.forEach(
            statCounter -> logOutput(printer.print(statCounter.countStatistic(finalLogRecords))));
    }
}
