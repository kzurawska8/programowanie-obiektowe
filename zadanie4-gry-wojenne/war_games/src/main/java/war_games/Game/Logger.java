package war_games.Game;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static final String LOG_FILE = "game_log.txt";

    public static void log(String message) {
        log(message, false);
    }

    public static void log(String message, boolean printToConsole) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(message + "\n");
        } 
        catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
        if (printToConsole) {
            System.out.println(message);
        }
    }
}