package fr.lernejo.navy_battle.launch;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.api.Ping;
import fr.lernejo.navy_battle.api.StartGame;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {
<<<<<<< HEAD:src/main/java/fr/lernejo/navy_battle/launch/Server.java
    final HttpServer server;
=======

    public final HttpServer server;
>>>>>>> f305f763a544b16699fa6611a663993c3dbdd743:src/main/java/fr/lernejo/navy_battle/Server.java

    public Server(int port) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        this.server.setExecutor(Executors.newSingleThreadExecutor());
        this.server.createContext("/api/game/start", new StartGame(port));
        this.server.createContext("/ping", new Ping());
        this.server.start();
    }

}
