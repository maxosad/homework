package edu.project3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
//    public static void main(String[] args) {
//        System.out.println(Arrays.toString(args));
//        //    java -jar build.jar --path logs/2023* --from 2023-08-31 --format markdown
////[--path, logs/2023*, --from, 2023-08-31, --format, markdown]
//    }

    private Main() { }
    public static Set<String> KEYS = new HashSet<>(List.of("--path", "--from", "--to", "--format"));
    public static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        LOGGER.info(KEYS.contains("--to"));

    }
}
