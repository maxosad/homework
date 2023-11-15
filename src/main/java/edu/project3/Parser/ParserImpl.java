package edu.project3.Parser;

import edu.project3.model.LogRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParserImpl implements Parser {

    @Override
    public List<LogRecord> parse(String regOrURL) {
//        try {
//            var uri = URI.create(path.toString());
//            URL.of()
//        } catch (IllegalArgumentException ignore) { }
//
//        Files.
//        try (HttpClient client = HttpClient.newHttpClient()) {
//            HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(ALL_JSON_URI))
//                .build();
//
//
//            HttpResponse<String> response =
//                client.send(request,  HttpResponse.BodyHandlers.of);
//
//            String[] split = response.body().split("[,\\[\\]]");
        //local handler reg
        Path dir = Path.of(regOrURL);
        try (var files = Files.list(dir)) {
            return files.flatMap(ParserImpl::parseFile).toList();

//            System.out.println(Arrays.toString(files.toArray()));
//            [src\main\java\edu\project3\data\logs.txt, src\main\java\edu\project3\data\secondFile.txt]

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<LogRecord> parseFile(Path filePath) {
        List<LogRecord> ans = new ArrayList<>();
        try(var reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            while(reader.ready()) {
                ans.add(parseLog(reader.readLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ans.stream();
    }

    public static LogRecord parseLog(String line) {
//80.91.33.133  -       -     [17/May/2015:11:05:56 +0000] "GET /downloads/product_1 HTTP/1.1" 404       337                    "-"       "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)"
//'$remote_addr - $remote_user [$time_local]            ' '"$request"                         $status $body_bytes_sent ' '"$http_referer" "$http_user_agent"'

        var split = line.split(" ");
//        [144.76.160.62, -, -, [17/May/2015:14:05:22, +0000], "GET, /downloads/product_2, HTTP/1.1", 404, 338, "-", "Debian, APT-HTTP/1.3, (1.0.1ubuntu2)"]
        String address = split[0].substring(1);
        String user = split[2];
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy:HH:mm:ss±hhmm", Locale.ENGLISH);
//        LocalDateTime date = LocalDateTime.parse(split[3].substring(1).concat(split[4]), formatter);
//        93.180.71.3 - - [17/May/2015:08:05:32 +0000] "GET /downloads/product_1 HTTP/1.1" 304 0 "-" "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy:HH:mm:ss", Locale.ENGLISH);
        LocalDateTime date = LocalDateTime.parse(split[3].substring(1), formatter);
        String request = split[5].substring(1);
        String resource = split[6];
        String status = split[8];
        String bodyBytes = split[9];
        String httpReferer = split[10];
        String httpUserAgent = split[11].substring(1).concat(" " + split[12] + (split.length == 14 ? " " + split[13].substring(0,split[13].length()-2) : ""));

        return new LogRecord(address,
            user,
            date,
            request,
            resource,
            status,
            bodyBytes,
            httpReferer,
            httpUserAgent
        );
//        return null;
    }
}
