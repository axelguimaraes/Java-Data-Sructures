package mygame.io;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mygame.game.*;
import mygame.structures.classes.ArrayUnorderedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ExportGameData {
    /**
     * Method to return all the data of the Map, it includes Portals and Connectors
     *
     * @param gameMap map of the game
     */
    public void mapExportToJson(GameMap gameMap) {

        ArrayUnorderedList<Portal> portals = new ArrayUnorderedList<>();
        ArrayUnorderedList<Connector> connectors = new ArrayUnorderedList<>();

        Iterator<Local> it = gameMap.getMap().iteratorBFS(0);
        while (it.hasNext()) {
            Local local = it.next();
            if (local instanceof Portal) {
                portals.addToRear((Portal) local);
            }
            if (local instanceof Connector) {
                connectors.addToRear((Connector) local);
            }


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
                if (portal.getConqueror() == null) {
                    ownership.put("player", "None");
                } else {
                    JSONObject conqueror = new JSONObject();
                    conqueror.put("ID", portal.getConqueror().getId());
                    conqueror.put("name", portal.getConqueror().getName());
                    conqueror.put("energy", portal.getConqueror().getEnergy());
                    conqueror.put("team", portal.getConqueror().getTeam().toString());
                    conqueror.put("level", portal.getConqueror().getLevel());
                    conqueror.put("current position", portal.getConqueror().getCurrentPositionID());
                    conqueror.put("XP", portal.getConqueror().getXp());
                    conqueror.put("maxEnergy", portal.getConqueror().getMaxEnergy());

                    ownership.put("player",conqueror);
                }

                portalObject.put("ownership", ownership);
                portalObject.put("gameSettings", gameSettings);

                JSONArray pathsArray = new JSONArray();
                for (Integer integer : portal.getPathsTo()) {
                    pathsArray.add(integer);
                }
                portalObject.put("pathTo", pathsArray);

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

                JSONArray pathsArray = new JSONArray();
                for (Integer integer : connector.getPathsTo()) {
                    pathsArray.add(integer);
                }
                connectorObject.put("pathTo", pathsArray);


                // Add the connector object to the connector array
                connectorArray.add(connectorObject);
            }
            // Add the connector array to the data object
            data.put("Connector", connectorArray);

            writeFile(data, "Map");
        }
    }


    /**
     * Method to export all the Player Data into a Json file
     *
     * @param players
     */
    public void playersExportToJson(ArrayUnorderedList<Player> players) {

        // Create a JSONObject to store the data
        JSONObject data = new JSONObject();
        // Create a JSONArray for the Players
        JSONArray playerArray = new JSONArray();
        // Iterate through each Player in the list
        for (Player player : players) {
            // Create a JSONObject for each player
            JSONObject playerObject = new JSONObject();
            playerObject.put("id", player.getId());
            playerObject.put("name", player.getName());
            playerObject.put("team", player.getTeam().toString());
            playerObject.put("level", player.getLevel());
            playerObject.put("experiencePoints", player.getXp());
            playerObject.put("maxEnergy", player.getMaxEnergy());
            playerObject.put("currentEnergy", player.getEnergy());

            // Add the player object to the portal array
            playerArray.add(playerObject);
        }
        // Add the player array to the data object
        data.put("Player", playerArray);

        writeFile(data, "Player");
    }

    public void locationsExportToJson(GameMap gameMap) {
        ArrayUnorderedList<Portal> portals = new ArrayUnorderedList<>();
        ArrayUnorderedList<Connector> connectors = new ArrayUnorderedList<>();
        ArrayUnorderedList<Player> players = gameMap.getPlayersInGame();

        Iterator<Local> it = gameMap.getMap().iteratorBFS(0);
        while (it.hasNext()) {
            Local local = it.next();

            if (local instanceof Portal) {
                portals.addToRear((Portal) local);
            } else {
                connectors.addToRear((Connector) local);
            }
        }

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
            if (portal.getConqueror() != null) {
                ownership.put("player", portal.getConqueror().getName());
            } else {
                ownership.put("player", "N/A");
            }

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
            playerObject.put("team", player.getTeam().toString());
            playerObject.put("level", player.getLevel());
            playerObject.put("experiencePoints", player.getXp());
            playerObject.put("maxEnergy", player.getMaxEnergy());
            playerObject.put("currentEnergy", player.getEnergy());

            // Add the player object to the portal array
            playerArray.add(playerObject);
        }
        // Add the player array to the data object
        data.put("Player", playerArray);

        try (FileWriter file = new FileWriter("files/gameData.json")) {
            file.write(data.toJSONString());
            System.err.println("Successfully exported data to JSON file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void writeFile(JSONObject data, String type) {
        if (data == null) {
            System.err.println("Data is null, cannot export to JSON file.");
            return;
        }
        String fileName = "";

        if (type.equals("Map")) {
            fileName = "files/map.json";
        } else if (type.equals("Player")) {
            fileName = "files/players.json";
        } else {
            System.err.println("Error: Invalid data type.");
            return;
        }
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(data.toJSONString());
            System.err.println("Successfully exported " + type + " data to JSON file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


