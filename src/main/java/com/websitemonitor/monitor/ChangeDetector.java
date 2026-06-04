package com.websitemonitor.monitor;

import com.websitemonitor.comparator.ContentComparator;
import java.io.IOExeption;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRespone;
import java.net.http.HttpResponse;
import java.util.*;

public class ChangeDetector {
    private final ContentComparator comparator;
    private final Httpclient client = HttpClient.newHttpClient();

    public ChangeDetector(ContentComparator comparator) {
        this.comparator = comparator;
    }

    private String fetchContent(String url) throws IOException, InterruptedException{
        //Builder to create new instance of the request
        //It requires a URI not a URL so we have to change the type 
        //Next we use the get method 
        //build() to get the instance back
        HttpRequest request = HttpRequest.Builder()
                                .uri(URI.create(url))
                                .GET()
                                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }   

}