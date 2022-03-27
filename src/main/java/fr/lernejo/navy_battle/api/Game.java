package fr.lernejo.navy_battle.api;

import fr.lernejo.navy_battle.json.JacksonJson;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpRequest;

public class Game {
    public final JacksonJson JsonAdverse;
    public final JacksonJson MonJson;

    public Game(JacksonJson BodyEnnReq, JacksonJson MaReq) {
        this.MonJson = MaReq;
        this.JsonAdverse = BodyEnnReq;
    }

    public void startNewGame() throws IOException, InterruptedException {
        HttpClient sendWeb = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(JsonAdverse.url.substring(1, JsonAdverse.url.length() - 1) + "/api/game/fire?cell=B2"))
            .setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").GET().build();
        HttpResponse<String> response = sendWeb.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
