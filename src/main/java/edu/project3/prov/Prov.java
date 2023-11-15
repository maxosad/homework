package edu.project3.prov;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Prov {
    public static void main(String[] args) {
        String line = "144.76.160.62 - - [17/May/2015:14:05:22 +0000] \"GET /downloads/product_2 HTTP/1.1\" 404 338 \"-\" \"Debian APT-HTTP/1.3 (1.0.1ubuntu2)\"";
        var split = line.split(" ");
        System.out.println(Arrays.toString(split));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy:HH:mm:ss±hhmm", Locale.ENGLISH);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy:HH:mm:ss", Locale.ENGLISH);
        String d = "17/May/2015";
        String a1 = "17/May/2015:14:05:22";
        String b1 = "+0000";
//        LocalDate date = LocalDate.parse(a1.concat(b1), formatter);
        LocalDateTime date = LocalDateTime.parse(a1, formatter);
        System.out.println(date);

    }
}
