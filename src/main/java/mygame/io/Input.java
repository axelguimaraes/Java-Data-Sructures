package mygame.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import mygame.game.GameMap;
import mygame.game.Local;
import mygame.structures.lists.UnorderedArrayList;
import mygame.structures.lists.UnorderedListADT;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Input {

    public static GameMap importGameMap() throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UnorderedArrayList.class, new UnorderedListTypeAdapter());
        Gson gson = gsonBuilder.create();

        String json = new String(Files.readAllBytes(Paths.get("files/gameMap.json")));
        GameMap gameMap = gson.fromJson(json, GameMap.class);

        return gameMap;
    }

    private static class UnorderedListTypeAdapter extends TypeAdapter<UnorderedListADT> {
        private Gson gson;
        public UnorderedListTypeAdapter(){
            gson = new Gson();
        }

        @Override
        public void write(JsonWriter jsonWriter, UnorderedListADT unorderedListADT) throws IOException {
            jsonWriter.beginObject();
            jsonWriter.name("list").beginArray();

            for (Object obj : unorderedListADT) {
                jsonWriter.value(obj.toString());
            }
            jsonWriter.endArray();
            jsonWriter.endObject();

        }

        @Override
        public UnorderedArrayList read(JsonReader jsonReader) throws IOException {
            UnorderedArrayList unorderedList = new UnorderedArrayList();
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String name = jsonReader.nextName();
                if (name.equals("list")) {
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        JsonToken token = jsonReader.peek();
                        if (token.equals(JsonToken.NULL)) {
                            jsonReader.nextNull();
                        } else {
                            Local local = gson.fromJson(jsonReader, Local.class);
                            unorderedList.addToRear(local);
                        }
                    }
                    jsonReader.endArray();
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            return unorderedList;
        }
    }

}

