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

public class InputClasses2 {

        public static void main(String[] args) {
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(new FileReader("files/exemploNovo.json"));
                JSONObject jsonObject = (JSONObject) obj;

                JSONArray connectors = (JSONArray) jsonObject.get("Connector");
                JSONArray portals = (JSONArray) jsonObject.get("Portal");
                JSONArray players = (JSONArray) jsonObject.get("Players");

                //Read Connectors
                ArrayUnorderedList<Connector> connectorList = new ArrayUnorderedList<>();
                for (Object connector : connectors) {
                    JSONObject connectorJson = (JSONObject) connector;
                    // extract data from localJson and set it in a Local object
                    JSONObject coordinatesJson = (JSONObject) connectorJson.get("coordinates");
                    double latitude = (Double) coordinatesJson.get("latitude");
                    double longitude = (Double) coordinatesJson.get("longitude");
                    Coordinates coordinates = new Coordinates(latitude, longitude);

                    Object gameSettingsObject = connectorJson.get("gameSettings");
                    JSONObject gameSettings = (JSONObject) gameSettingsObject;
                    int energy = (((Long) gameSettings.get("energy")).intValue());
                    int cooldown = (((Long) gameSettings.get("energy")).intValue());


                    Connector connectorObj = new Connector(energy, coordinates , cooldown);

                    // set data in localObj
                    connectorList.addToRear(connectorObj);

                }

                // Do something with the JSON arrays
                for (Connector connector : connectorList) {
                    System.out.println(connector.toString());
                }

                //Read Portals
                ArrayUnorderedList<Portal> portalList = new ArrayUnorderedList<>();
                for (Object portal : portals) {
                    Player player = new Player();

                    JSONObject portalJson = (JSONObject) portal;

                    // extract data from portalJson and set it in a Portal object

                    String name = (String) portalJson.get("name");

                    //Object coordinatesObject = portalJson.get("coordinates");
                    //JSONObject coordinatesObject1 = (JSONObject) coordinatesObject;
                    JSONObject coordinatesJson = (JSONObject) portalJson.get("coordinates");
                    double latitude = (Double) coordinatesJson.get("latitude");
                    double longitude = (Double) coordinatesJson.get("longitude");
                    Coordinates coordinates = new Coordinates(latitude, longitude);

                    Object gameSettingsObject = portalJson.get("gameSettings");
                    JSONObject gameSettings = (JSONObject) gameSettingsObject;

                    //Long energyLong = (Long) portalJson.get("energy");
                    //int energy = energyLong.intValue();
                    int energy=(((Long) gameSettings.get("energy")).intValue());
                    //Long maxEnergyLong = (Long) portalJson.get("maxEnergy");
                    //int maxEnergy = maxEnergyLong.intValue();
                    int maxEnergy = (((Long) gameSettings.get("maxEnergy")).intValue());

                    //JSONObject ownership = (JSONObject) gameSettings.get("ownership");
                    if(gameSettings.get("ownership") != null){
                        JSONObject ownershipJson = (JSONObject) gameSettings.get("ownership");
                        player.setName((String) ownershipJson.get("player"));
                    }

                    Portal portalObj = new Portal(name, coordinates, energy, maxEnergy , player);
                    // set data in routeObj
                    portalList.addToRear(portalObj);

                }
                // Do something with the JSON arrays
                for (Portal portal : portalList) {
                    System.out.println(portal.toString());
                }


                ArrayUnorderedList<Player> playerList = new ArrayUnorderedList<>();
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
                    int maxEnergy = maxEnergyLong.intValue();
                    Long currentEnergyLong = (Long) playerJson.get("currentEnergy");
                    int currentEnergy = currentEnergyLong.intValue();

                    // extract data from playerJson and set it in a Player object
                    Player playerObj = new Player(name,team,level,experiencePoints,maxEnergy,currentEnergy);
                    // set data in playerObj
                    playerList.addToRear(playerObj);
                }
                // Do something with the JSON arrays
                for (Player player : playerList) {
                    System.out.println(player.showPlayerData());
                }

            } catch (IOException | ParseException e) {
                e.printStackTrace();
            } catch (PlayerWithNoTeamException e) {
                throw new RuntimeException(e);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }

}
