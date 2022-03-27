package fr.lernejo.navy_battle.Api;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpRequest;

public class PostHandler {

    public final void post(int port, String addrUrl) throws IOException, InterruptedException {
        HttpRequest postReq = HttpRequest.newBuilder().uri(URI.create(addrUrl + "/api/game/start"))
            .setHeader("Accept", "application/json").setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + port + "\", \"message\":\"Que le meilleure gagne!\"}")).build();
        HttpClient web = HttpClient.newHttpClient();
        HttpResponse<String> result = web.send(postReq, HttpResponse.BodyHandlers.ofString());
        System.out.println(result.body());
    }
}
