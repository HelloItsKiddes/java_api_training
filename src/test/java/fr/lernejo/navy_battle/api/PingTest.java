package fr.lernejo.navy_battle.api;

import fr.lernejo.navy_battle.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class PingTest {

    @Test
    void testOfPing() throws IOException, InterruptedException {

        Server serv = new Server(9798);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:9798/ping")).GET().build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals("http://localhost:9798/ping GET",request.toString());
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("OK", response.body());
    }
}
