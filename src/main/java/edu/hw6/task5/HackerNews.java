package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private HackerNews() { }

    public static final String ALL_JSON_URI = "https://hacker-news.firebaseio.com/v0/topstories.json";
    public static final int TITLE_BEGIN_INDEX = 9;
    public static final int PARENT_BEGIN_INDEX = 9;

    public static long[] hackerNewsTopStories() {
        long[] ans = new long[0];
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ALL_JSON_URI))
                .build();


            HttpResponse<String> response =
                client.send(request,  HttpResponse.BodyHandlers.ofString());

            String[] split = response.body().split("[,\\[\\]]");
            int splitLength = split.length;
            ans = new long[splitLength - 1];
            for (int i = 1; i < splitLength; i++) {
                ans[i - 1] = Long.parseLong(split[i]);
            }
//            System.out.println(Arrays.toString(split));
        } catch (IOException | InterruptedException ignore) { }

        return ans;
    }

    public static String news(long id) {
        String uri = "https://hacker-news.firebaseio.com/v0/item/%d.json".formatted(id);
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

            HttpResponse<String> response =
                client.send(request,  HttpResponse.BodyHandlers.ofString());

//            System.out.println(response.body());
            String responseBody = response.body();
            Matcher titleMatcher = Pattern.compile("\"title\":\"[^\"]+\"").matcher(responseBody);
            if (!titleMatcher.find()) {
                Matcher parentMatcher = Pattern.compile("\"parent\":\\d+").matcher(responseBody);
                parentMatcher.find();
                String parentJSONFormat = parentMatcher.group();
                long parent = Long.parseLong(parentJSONFormat.substring(PARENT_BEGIN_INDEX));

                return news(parent);
            }
            String titleJSONFormat = titleMatcher.group();

            return titleJSONFormat.substring(TITLE_BEGIN_INDEX, titleJSONFormat.length() - 2);
        } catch (IOException | InterruptedException ignore) {
            return "";
        }
    }
}
