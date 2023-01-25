package mygame.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mygame.game.GameMap;
import mygame.game.Local;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Output {

    public static void exportGameMap(GameMap<Local> gameMap) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.serializeNulls();

        Gson gson = gsonBuilder.create();

        try (Writer writer = new FileWriter("files/gameMap.json")){
            writer.write(gson.toJson(gameMap));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
