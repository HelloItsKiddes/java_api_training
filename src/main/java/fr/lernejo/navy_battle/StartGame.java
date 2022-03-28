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
            Game party = new Game(reqBody, reqBody);
            try { party.startNewGame(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public StartGame(int port) {
        this.URL.append("http://localhost:").append(port);
    }

    private void errorMess(HttpExchange exchange) throws IOException {
        String messageErr = "Not Found !";
        exchange.sendResponseHeaders(404, messageErr.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(messageErr.getBytes());

        }
    }
    private void resultMess(HttpExchange httpExchange, String message, int code) throws IOException {
        httpExchange.sendResponseHeaders(code, message.length());
        try (OutputStream os = httpExchange.getResponseBody()) {
            os.write(message.getBytes());
        }
    }
    private JacksonJson parser(HttpExchange httpExchange) throws IOException {
        JacksonJson json = null;
        String streamString = toStringStream(httpExchange.getRequestBody());
        ObjectMapper mapper = new ObjectMapper();

        if (streamString.isBlank()) {
            return null;
        }
        else {
            try {
                json = mapper.readValue(streamString, JacksonJson.class);
            } catch (IllegalArgumentException e) {
                httpExchange.sendResponseHeaders(404, "Not Found !".length());
            }
        }
        return json;
    }



    private String toStringStream(InputStream str) throws IOException {
        int compteur=1;
        StringBuilder stream = new StringBuilder();
        while (compteur>0){
            compteur = str.read();
            stream.append((char) compteur);
        }
        return stream.toString();
    }


}
