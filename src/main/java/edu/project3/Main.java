package edu.project3;

import edu.project3.model.OutputFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.file.Path;
import java.text.Format;
import java.time.LocalDate;
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

    private Main() { }
    public static Set<String> KEYS = new HashSet<>(List.of("--path", "--from", "--to", "--format"));
    public static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        LOGGER.info(KEYS.contains("--to"));


        Path path;
        LocalDate fromDate ;
        LocalDate toDate;
        Format format;

//        Map<String, String> m = new HashMap<>();
        int argsLength = args.length;
        for (int i = 0; i < argsLength; i += 2) {
            if (KEYS.contains(args[i])) {
                switch (args[i]) {
                    case "--path" -> path = Path.of(args[i + 1]);
                    case "--from" -> fromDate = LocalDate.of(args[i + 1]);
                    case "--to" -> toDate = LocalDate.of(args[i + 1]);
                    case "--format" -> format = Format;
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

    }
}
