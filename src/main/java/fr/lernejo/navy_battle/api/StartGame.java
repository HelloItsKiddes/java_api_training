package fr.lernejo.navy_battle.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.lernejo.navy_battle.json.JacksonJson;

public class StartGame implements HttpHandler {

    private final StringBuilder URL = new StringBuilder();
    public StartGame(int port) {
        this.URL.append("http://localhost:").append(port);
    }

    @Override
    public void handle(HttpExchange hExchange) throws IOException {
        if (!hExchange.getRequestMethod().equals("POST")) {
            System.out.println("Error");
        }
        else {
            JacksonJson reqBody = parser(hExchange);
            if (reqBody == null || reqBody.id.equals("\"\"") || reqBody.message.equals("\"\"") || reqBody.url.equals("\"\"")  ) {
                resultMessage(hExchange, "Erreur de formattage", 400);}
            else {resultMessage(hExchange, "{\n\t\"id\":\"" + UUID.randomUUID() + "\",\n\t\"url\":\"" + this.URL + "\",\n\t\"message\":\"C'est partit\"\n}", 202);}
            var party = new Game(reqBody, reqBody);
            try {party.startNewGame();} catch (InterruptedException e) {e.printStackTrace();}
        }
    }
    private JacksonJson parser(HttpExchange hExchange) throws IOException {
        JacksonJson requete = null;
        ObjectMapper mapper = new ObjectMapper();
        String streamString = convertToString(hExchange.getRequestBody());
        if (streamString.isBlank()) {
            return null;
        }
        else {
            try {requete = mapper.readValue(streamString, JacksonJson.class);}
            catch (IllegalArgumentException e) {
                hExchange.sendResponseHeaders(400, "Not Found !".length());
                throw new IllegalArgumentException();
            }
        }
        return requete;
    }

    private String convertToString(InputStream str) throws IOException {
        StringBuilder stream = new StringBuilder();
        int i;
        while ((i = str.read()) > 0) {
            stream.append((char) i);
        }
        return stream.toString();
    }

    private void resultMessage(HttpExchange exchange, String message, int codeReq) throws IOException {
        exchange.sendResponseHeaders(codeReq, message.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(message.getBytes());
        }
    }

}
