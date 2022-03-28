package fr.lernejo.navy_battle;
import java.io.IOException;
public class Launcher {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server(Integer.parseInt(args[0]));
        if (args.length > 1) {
            new PostHandler().post(Integer.parseInt(args[0]), args[1]);
        }
    }
}

