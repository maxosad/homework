package edu.project3.Parser;

import edu.project3.model.LogRecord;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.List;

public class ParserImpl implements Parser {

    @Override
    public List<LogRecord> parse(Path path) {
        try {
            var uri = URI.create(path.toString());
        } catch (IllegalArgumentException ignore) { }

        Files.
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ALL_JSON_URI))
                .build();


            HttpResponse<String> response =
                client.send(request,  HttpResponse.BodyHandlers.ofString());

            String[] split = response.body().split("[,\\[\\]]");

        return null;
    }
}
