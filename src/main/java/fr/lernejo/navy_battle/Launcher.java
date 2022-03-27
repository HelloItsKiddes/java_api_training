package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.Api.PostHandler;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = Integer.parseInt(args[0]);
        var server = new Server(port);
        if (args.length > 1) {
            new PostHandler().post(port, args[1]);
        }
    }
}

