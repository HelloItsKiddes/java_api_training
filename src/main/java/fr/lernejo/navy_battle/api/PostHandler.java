package fr.lernejo.navy_battle.api;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpRequest;

public class PostHandler {

    public final void post(int port, String adversaryUrl) throws IOException, InterruptedException {

        HttpClient sendWeb = HttpClient.newHttpClient();
        HttpRequest postReq = HttpRequest.newBuilder().uri(URI.create(adversaryUrl + "/api/game/start"))
            .setHeader("Accept", "application/json").setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + port + "\", \"message\":\"Que le meilleure gagne!\"}")).build();
        HttpResponse<String> result = sendWeb.send(postReq, HttpResponse.BodyHandlers.ofString());

    }
}
