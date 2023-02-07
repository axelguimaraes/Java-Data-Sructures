package mygame.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mygame.game.GameSettings;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class that deals with import/export of global game settings from/to a JSON file
 */
public class IOGameSettings {

    /**
     * Imports global game settings
     * @return {@link GameSettings} class with global settings
     * @throws IOException thrown when there's a problem reading the file
     */
    public static GameSettings importData() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Reader reader = Files.newBufferedReader(Paths.get("files/gameSettings.json"));
        return gson.fromJson(reader, GameSettings.class);
    }

    /**
     * Exports global game settings
     * @param gameSettings {@link GameSettings} class to export
     * @throws IOException thrown when there's a problem writing to the file
     */
    public static void exportData(GameSettings gameSettings) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(gameSettings, new FileWriter("files/gameSettings.json"));
    }
}
