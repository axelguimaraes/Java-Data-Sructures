package mygame.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonObject;
import mygame.game.*;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Input {

    /*
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

     */

    public static GameMap importGameMap() throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Local.class, new LocalInstanceCreator());
        Gson gson = gsonBuilder.create();

        String json = new String(Files.readAllBytes(Paths.get("files/gameMap.json")));

        return gson.fromJson(json, GameMap.class);
    }

    private static class LocalInstanceCreator implements InstanceCreator<Local> {

        @Override
        public Local createInstance(Type type) {
            return null;
        }

        public Local createInstance(JsonObject json) {
            String localType = json.get("localType").getAsString();
            if (localType.equals(LocalType.CONNECTOR.toString())) {
                return new Gson().fromJson(json, Connector.class);
            } else if (localType.equals(LocalType.PORTAL.toString())) {
                return new Gson().fromJson(json, Portal.class);
            } else {
                return null;
            }
        }
    }
}

