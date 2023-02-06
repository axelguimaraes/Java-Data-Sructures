package mygame.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mygame.game.GameSettings;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOGameSettings {
    public static GameSettings importData() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Reader reader = Files.newBufferedReader(Paths.get("files/gameSettings.json"));
        return gson.fromJson(reader, GameSettings.class);
    }

    public static void exportData(GameSettings gameSettings) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(gameSettings, new FileWriter("files/gameSettings.json"));
    }
}
