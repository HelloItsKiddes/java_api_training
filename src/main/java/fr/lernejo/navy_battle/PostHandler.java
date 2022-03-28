package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpRequest;

public class PostHandler {

    public final void post(int port, String Url) throws IOException, InterruptedException {
        HttpRequest postReq = HttpRequest.newBuilder().uri(URI.create(Url + "/api/game/start"))
            .setHeader("Accept", "application/json").setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + port + "\", \"message\":\"test\"}")).build();
        HttpClient web = HttpClient.newHttpClient();
        HttpResponse<String> result = web.send(postReq, HttpResponse.BodyHandlers.ofString());
    }
}
