package mygame.io;
import mygame.exceptions.PlayerWithNoTeamException;
import mygame.game.*;
import mygame.structures.classes.ArrayUnorderedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;

public class InputClasses {
    private JSONParser parser;
    private JSONArray connectors;
    private JSONArray portals;
    private JSONArray players;
    private ArrayUnorderedList<Connector> connectorList;
    private ArrayUnorderedList<Portal> portalList;

    private ArrayUnorderedList<Player> playerList;

    public InputClasses() {
        parser = new JSONParser();
    }

    public void parseJSON(String filePath) {
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            connectors = (JSONArray) jsonObject.get("Connector");
            portals = (JSONArray) jsonObject.get("Portal");
            players = (JSONArray) jsonObject.get("Players");
        } catch (Exception e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
    }

    public void readConnectors() {
        connectorList = new ArrayUnorderedList<>();
        for (Object connector : connectors) {
            JSONObject connectorJson = (JSONObject) connector;
            JSONObject coordinatesJson = (JSONObject) connectorJson.get("coordinates");
            double latitude = (Double) coordinatesJson.get("latitude");
            double longitude = (Double) coordinatesJson.get("longitude");
            Coordinates coordinates = new Coordinates(latitude, longitude);

            JSONObject gameSettings = (JSONObject) connectorJson.get("gameSettings");
            int energy = (((Long) gameSettings.get("energy")).intValue());
            int cooldown = (((Long) gameSettings.get("energy")).intValue());

            Connector connectorObj = new Connector(energy, coordinates, cooldown);
            connectorList.addToRear(connectorObj);
            
        }

        for (Connector connectors : connectorList) {
            System.out.println(connectors.toString());
        }
    }

    public void readPortals() {
        portalList = new ArrayUnorderedList<>();
        for (Object portal : portals) {
            Player player = new Player();
            JSONObject portalJson = (JSONObject) portal;
            String name = (String) portalJson.get("name");
            JSONObject coordinatesJson = (JSONObject) portalJson.get("coordinates");
            double latitude = (Double) coordinatesJson.get("latitude");
            double longitude = (Double) coordinatesJson.get("longitude");
            Coordinates coordinates = new Coordinates(latitude, longitude);

            JSONObject gameSettings = (JSONObject) portalJson.get("gameSettings");
            int energy = (((Long) gameSettings.get("energy")).intValue());
            int maxEnergy = (((Long) gameSettings.get("maxEnergy")).intValue());

            if (gameSettings.get("ownership") != null) {
                JSONObject ownershipJson = (JSONObject) gameSettings.get("ownership");
                player.setName((String) ownershipJson.get("player"));
            }

            Portal portalObj = new Portal(name, coordinates, energy, maxEnergy, player);
            portalList.addToRear(portalObj);

        }
        for (Portal portals : portalList) {
            System.out.println(portals.toString());
        }

    }

    public void readPlayers() throws PlayerWithNoTeamException {
        playerList = new ArrayUnorderedList<>();
        for (Object player : players) {
            JSONObject playerJson = (JSONObject) player;
            Team team = null;

            String name = (String) playerJson.get("name");
            String teamString = (String) playerJson.get("team");

            switch (teamString.toUpperCase()) {
                case "SPARKS":
                    team = Team.SPARKS;
                    break;
                case "GIANTS":
                    team = Team.GIANTS;
                    break;
            }

            Long levelLong = (Long) playerJson.get("level");
            int level = levelLong.intValue();
            Double experiencePoints = ((Long) playerJson.get("experiencePoints")).doubleValue();
            Long maxEnergyLong = (Long) playerJson.get("maxEnergy");
            //int maxEnergy = maxEnergyLong.intValue();
            //Long currentEnergyLong = (Long) playerJson.get("currentEnergy");
            //int currentEnergy = currentEnergyLong.intValue();
            int maxEnergy = (((Long) playerJson.get("maxEnergy")).intValue());
            int currentEnergy = (((Long) playerJson.get("currentEnergy")).intValue());
            //int maxEnergy = (((Long) gameSettings.get("maxEnergy")).intValue());

            // extract data from playerJson and set it in a Player object
            Player playerObj = new Player(name,team,level,experiencePoints,maxEnergy,currentEnergy);
            // set data in playerObj
            playerList.addToRear(playerObj);
        }
        for (Player players : playerList) {
            System.out.println(players.showPlayerData());
        }
    }

    public static void main(String[] args) throws PlayerWithNoTeamException {
        InputClasses inputClasses = new InputClasses();
        inputClasses.parseJSON("files/exemploNovo.json");
        inputClasses.readPlayers();

    }
}