package mygame.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mygame.game.GameMap;

import java.io.*;
import java.lang.reflect.Type;

public class Output {

    public static void exportGameMap(GameMap gameMap) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.serializeNulls();
        gsonBuilder.serializeSpecialFloatingPointValues();

        Gson gson = gsonBuilder.create();

        try (Writer writer = new FileWriter("files/gameMap.json")){
            writer.write(gson.toJson(gameMap));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
