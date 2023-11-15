package edu.project3;

import edu.project3.Parser.Parser;
import edu.project3.Parser.ParserImpl;
import edu.project3.Printer.BridgePrinter;
import edu.project3.Printer.Printer;
import edu.project3.StatisticCounter.AvgBodyBytes;
import edu.project3.StatisticCounter.CommonStatisticCounter;
import edu.project3.StatisticCounter.CountRequests;
import edu.project3.StatisticCounter.EarlyDate;
import edu.project3.StatisticCounter.MostFrequentCode;
import edu.project3.StatisticCounter.MostFrequentResource;
import edu.project3.StatisticCounter.StatisticCounter;
import edu.project3.model.LogRecord;
import edu.project3.model.OutputFormat;
import edu.project3.model.statistic.Statistic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.file.Path;
import java.text.Format;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
//    public static void main(String[] args) {
//        System.out.println(Arrays.toString(args));
//        //    java -jar build.jar --path logs/2023* --from 2023-08-31 --format markdown
////[--path, logs/2023*, --from, 2023-08-31, --format, markdown]
//    }
    public static OutputFormat DEFAULT_FORMAT = OutputFormat.ADOC;
    public static String DEFAULT_FORMAT_STRING = "adoc";

    private Main() { }
    public static Set<String> KEYS = new HashSet<>(List.of("--path", "--from", "--to", "--format"));
    public static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
//        LOGGER.info(KEYS.contains("--to"));


//        Path path = null;
        String path = null;
        LocalDate fromDate = null ;
        LocalDate toDate = null;
        String format = DEFAULT_FORMAT_STRING;
//        Format format;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int argsLength = args.length;
        for (int i = 0; i < argsLength; i += 2) {
            if (KEYS.contains(args[i])) {
                switch (args[i]) {
                    case "--path" -> path = args[i + 1];
                    case "--from" -> fromDate = LocalDate.parse(args[i + 1], formatter);
                    case "--to" -> toDate  = LocalDate.parse(args[i + 1], formatter);
                    case "--format" -> format = args[i + 1];
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        Parser parser = new ParserImpl();
        List<LogRecord> logRecords = parser.parse(path);

//        StatisticCounter<Statistic<Map<String, Integer>>> commonStatisticCounter = new CommonStatisticCounter<>();
//        Statistic<Map<String, Integer>> commonStatistic = commonStatisticCounter.countStatistic(logRecords);
        for(var logRecord : logRecords) {
            System.out.println(logRecord);
        }
        var feq = new MostFrequentCode();
        Map<String, Integer> mm = feq.countStatistic(logRecords);
        System.out.println(mm.toString());

        var feq1 = new MostFrequentResource();
        Map<String, Integer> mm1 = feq1.countStatistic(logRecords);
        System.out.println(mm1.toString());

        var feq2 = new AvgBodyBytes();
        var mm2 = feq2.countStatistic(logRecords);
        System.out.println(mm2.toString());

        var feq3 = new CountRequests();
        var mm3 = feq3.countStatistic(logRecords);
        System.out.println(mm3.toString());
//        Printer printer = new BridgePrinter(format);
//        printer.print(commonStatistic);

        var feq4 = new EarlyDate();
        var mm4 = feq4.countStatistic(logRecords);
        System.out.println(mm4.toString());


    }
}
