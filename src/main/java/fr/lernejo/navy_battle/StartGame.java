package fr.lernejo.navy_battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class StartGame implements HttpHandler {

    private final StringBuilder URL = new StringBuilder();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (!httpExchange.getRequestMethod().equals("POST")) {
            errorMess(httpExchange);
        }
        else {
            JacksonJson reqBody = parser(httpExchange);
            if (reqBody.url.equals("\"\"") || reqBody.id.equals("\"\"") || reqBody.message.equals("\"\"") || reqBody == null) {
                resultMess(httpExchange, "Erreur : Pas le bon format", 400);
            }
            else {
                resultMess(httpExchange, "{\n\t\"id\":\"" + UUID.randomUUID() + "\",\n\t\"url\":\"" + this.URL + "\",\n\t\"message\":\"C'est partit\"\n}", 202);
            }
            var party = new Game(reqBody, reqBody);
            try { party.startNewGame(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public StartGame(int port) {
        this.URL.append("http://localhost:").append(port);
    }

    private void errorMess(HttpExchange exchange) throws IOException {
        String body = "Not Found !";
        exchange.sendResponseHeaders(404, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());

        }
    }
    private void resultMess(HttpExchange httpExchange, String message, int code) throws IOException {
        httpExchange.sendResponseHeaders(code, message.length());
        try (OutputStream os = httpExchange.getResponseBody()) {
            os.write(message.getBytes());
        }
    }
    private JacksonJson parser(HttpExchange httpExchange) throws IOException {
        JacksonJson body = null;
        ObjectMapper mapper = new ObjectMapper();
        String streamString = toStringStream(httpExchange.getRequestBody());
        if (streamString.isBlank()) {
            return null;
        }
        else {
            try {
                body = mapper.readValue(streamString, JacksonJson.class);
            } catch (IllegalArgumentException e) {
                httpExchange.sendResponseHeaders(400, "Not Found !".length());
            }
        }
        return body;
    }



    private String toStringStream(InputStream str) throws IOException {
        int compteur;
        StringBuilder stream = new StringBuilder();
        while ((compteur = str.read()) > 0) {
            stream.append((char) compteur);
        }
        return stream.toString();
    }


}
