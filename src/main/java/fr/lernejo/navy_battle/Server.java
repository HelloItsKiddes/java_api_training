package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.Api.Ping;
import fr.lernejo.navy_battle.Api.StartGame;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    final HttpServer server;

    Server(int port) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        this.server.setExecutor(Executors.newSingleThreadExecutor());
        this.server.createContext("/api/game/start", new StartGame(port));
        this.server.createContext("/ping", new Ping());
        this.server.start();
    }

}
