package mygame.io;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mygame.game.Connector;
import mygame.game.Local;
import mygame.game.Player;
import mygame.game.Portal;
import mygame.structures.classes.ArrayUnorderedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ExportGameData {
    public void exportToJson(ArrayUnorderedList<Portal> portals, ArrayUnorderedList<Connector> connectors, ArrayUnorderedList<Player> players) {
        // Create a JSONObject to store the data
        JSONObject data = new JSONObject();
        // Create a JSONArray for the portals
        JSONArray portalArray = new JSONArray();
        // Iterate through each portal in the list
        for (Portal portal : portals) {
            // Create a JSONObject for each portal
            JSONObject portalObject = new JSONObject();
            portalObject.put("id", portal.getId());
            portalObject.put("name", portal.getName());

            JSONObject coords = new JSONObject();
            coords.put("latitude", portal.getCoordinates().getLatitude());
            coords.put("longitude", portal.getCoordinates().getLongitude());
            portalObject.put("coordinates", coords);

            JSONObject gameSettings = new JSONObject();
            gameSettings.put("energy", portal.getEnergy());
            gameSettings.put("maxEnergy", portal.getMaxEnergy());


            JSONObject ownership = new JSONObject();
            ownership.put("player", portal.getConqueror().getName());
            gameSettings.put("ownership", ownership);
            portalObject.put("gameSettings", gameSettings);

            // Add the portal object to the portal array
            portalArray.add(portalObject);
        }

        data.put("Portal", portalArray);

        // Create a JSONArray for the connectors
        JSONArray connectorArray = new JSONArray();
        // Iterate through each connector in the list
        for (Connector connector : connectors) {
            // Create a JSONObject for each connector
            JSONObject connectorObject = new JSONObject();
            connectorObject.put("id", connector.getId());

            JSONObject coords = new JSONObject();
            coords.put("latitude", connector.getCoordinates().getLatitude());
            coords.put("longitude", connector.getCoordinates().getLongitude());
            connectorObject.put("coordinates", coords);

            JSONObject gameSettings = new JSONObject();
            gameSettings.put("energy", connector.getEnergy());
            gameSettings.put("cooldown", connector.getCooldown());
            connectorObject.put("gameSettings", gameSettings);

            // Add the connector object to the portal array
            connectorArray.add(connectorObject);
        }
        // Add the connector array to the data object
        data.put("Connector", connectorArray);

        // Create a JSONArray for the Players
        JSONArray playerArray = new JSONArray();
        // Iterate through each Player in the list
        for (Player player : players) {
            // Create a JSONObject for each player
            JSONObject playerObject = new JSONObject();
            playerObject.put("id", player.getId());
            playerObject.put("name", player.getName());
            playerObject.put("team", player.getTeam());
            playerObject.put("level", player.getLevel());
            playerObject.put("experiencePoints", player.getXp());
            playerObject.put("maxEnergy", player.getMaxEnergy());
            playerObject.put("currentEnergy", player.getEnergy());

            // Add the player object to the portal array
            playerArray.add(playerObject);
        }
        // Add the player array to the data object
        data.put("Player", playerArray);

        try (FileWriter file = new FileWriter("files/exemplo.json")) {
            file.write(data.toJSONString());
            System.out.println("Successfully exported data to JSON file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


