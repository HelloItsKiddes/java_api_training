package fr.lernejo.navy_battle.api;

import fr.lernejo.navy_battle.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StartGameTest {
    @Test
    void testReqAndResponse() throws IOException, InterruptedException, IllegalAccessException {
        Server serv = new Server(9999);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:9999/api/game/start"))
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + 9999 + "\", \"message\":\"test\"}")).build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(202, response.statusCode());
        Assertions.assertEquals("http://localhost:9999/api/game/start POST", request.toString());

    }
}
