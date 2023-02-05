package mygame.io;
import mygame.exceptions.PlayerWithNoTeamException;
import mygame.game.*;
import mygame.structures.classes.ArrayUnorderedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;

public class InputGameData {
    private JSONParser parser;
    private JSONArray connectors;
    private JSONArray portals;
    private JSONArray players;
    private JSONArray routes;
    private ArrayUnorderedList<Connector> connectorList;
    private ArrayUnorderedList<Portal> portalList;
    private ArrayUnorderedList<Player> playerList;

    private ArrayUnorderedList<Object> routesList;

    public InputGameData() {
        parser = new JSONParser();
    }

    /**
     * This method parses a JSON file located at the given file path and populates
     * the 'connectors', 'portals', and 'players' JSONArray fields with the corresponding data from the file.
     *
     * @param filePath The path to the JSON file to parse
     */
    public void parseJSON(String filePath) {
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            connectors = (JSONArray) jsonObject.get("Connector");
            portals = (JSONArray) jsonObject.get("Portal");
            players = (JSONArray) jsonObject.get("Players");
            routes = (JSONArray) jsonObject.get("routes");
        } catch (Exception e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
    }

    /**
     * This method reads the data from the 'connectors' JSONArray and creates Connector objects for each entry.
     * The resulting Connector objects are stored in the 'connectorList' ArrayUnorderedList.
     * Finally, the method prints the toString representation of each Connector in the 'connectorList'.
     */
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

    /**
     * Reads the portals from the JSON object and adds it to the portalList.
     * Finally, the method prints the toString representation of each Portal in the 'portalList'
     */
    public void readPortals() {
        portalList = new ArrayUnorderedList<>();
        for (Object portal : portals) {
            Player player = new Player();
            JSONObject portalJson = (JSONObject) portal;
            String name = (String) portalJson.get("name");

            // extract coordinates from the JSON object
            JSONObject coordinatesJson = (JSONObject) portalJson.get("coordinates");
            double latitude = (Double) coordinatesJson.get("latitude");
            double longitude = (Double) coordinatesJson.get("longitude");
            Coordinates coordinates = new Coordinates(latitude, longitude);

            // extract game settings from the JSON object
            JSONObject gameSettings = (JSONObject) portalJson.get("gameSettings");
            int energy = (((Long) gameSettings.get("energy")).intValue());
            int maxEnergy = (((Long) gameSettings.get("maxEnergy")).intValue());

            // extract the ownership information if it exists
            if (gameSettings.get("ownership") != null) {
                JSONObject ownershipJson = (JSONObject) gameSettings.get("ownership");
                player.setName((String) ownershipJson.get("player"));
            }

            // extract data from portalJson and set it in a Portal object
            Portal portalObj = new Portal(name, coordinates, energy, maxEnergy, player);
            // set data in portalObj
            portalList.addToRear(portalObj);

        }
        // print the portalList
        for (Portal portals : portalList) {
            System.out.println(portals.toString());
        }

    }

    /**
     * Reads the players from the JSON object and adds it to the playerList.
     * Finally, the method prints the data of each Player in the 'playerList' by using the 'showPlayerData()' method
     * @throws PlayerWithNoTeamException if a player does not have a team specified.
     */
    public void readPlayers() throws PlayerWithNoTeamException {
        playerList = new ArrayUnorderedList<>();
        for (Object player : players) {
            JSONObject playerJson = (JSONObject) player;
            Team team = null;

            // extract player data from the JSON object
            String name = (String) playerJson.get("name");
            String teamString = (String) playerJson.get("team");

            // convert the team string to a Team object
            switch (teamString.toUpperCase()) {
                case "SPARKS":
                    team = Team.SPARKS;
                    break;
                case "GIANTS":
                    team = Team.GIANTS;
                    break;
            }

            // extract the rest of the player data
            Long levelLong = (Long) playerJson.get("level");
            int level = levelLong.intValue();
            Double experiencePoints = ((Long) playerJson.get("experiencePoints")).doubleValue();
            int maxEnergy = (((Long) playerJson.get("maxEnergy")).intValue());
            int currentEnergy = (((Long) playerJson.get("currentEnergy")).intValue());

            // extract data from playerJson and set it in a Player object
            Player playerObj = new Player(name,team,level,experiencePoints,maxEnergy,currentEnergy);
            // set data in playerObj
            playerList.addToRear(playerObj);
        }

        // print the playerList
        for (Player players : playerList) {
            System.out.println(players.showPlayerData());
        }
    }

    /**
     * Reads the routes from the JSON object.
     */
    public void readRoutes(){
        routesList = new ArrayUnorderedList<>();
        for (Object route : routes){
            JSONObject routesJson = (JSONObject) route;

            int from = (((Long) routesJson.get("from")).intValue());
            int to = (((Long) routesJson.get("to")).intValue());
        }

    }

    public static void main(String[] args) throws PlayerWithNoTeamException {
        InputGameData inputClasses = new InputGameData();
        inputClasses.parseJSON("files/exemploNovo.json");
        inputClasses.readPlayers();
    }
}