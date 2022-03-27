package fr.lernejo.navy_battle;
import fr.lernejo.navy_battle.server.Server;

import java.io.IOException;
public class Launcher {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = Integer.parseInt("9999");
        Server server = new Server(port);
        if (args.length > 1) {
            new fr.lernejo.navy_battle.api.PostHandler().post(port, args[1]);
        }
    }
}

