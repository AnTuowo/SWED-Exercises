package com.websitemonitor.monitor;

import com.websitemonitor.comparator.ContentComparator;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ChangeDetector {
    private ContentComparator comparator;
    private final HttpClient client = HttpClient.newHttpClient();
    private final Map<String, String> lastSnapshot = new HashMap<>();

    public ChangeDetector(ContentComparator comparator) {
        this.comparator = comparator;
    }

    public void setComparator(ContentComparator comparator) {
        this.comparator = comparator;
    }

    private String fetchContent(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public boolean hasChanged(String url) throws IOException, InterruptedException {
        String newContent = fetchContent(url);
        if (newContent == null) {
            return false;
        }

        String previous = lastSnapshot.get(url);
        lastSnapshot.put(url, newContent);
        if (previous == null) {
            return false;
        }

        return comparator.hasChanged(previous, newContent);
    }
}