package mygame.io;
import mygame.exceptions.PlayerWithNoTeamException;
import mygame.game.Player;
import mygame.game.Team;
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
                Object obj = parser.parse(new FileReader("files/exemplo.json"));
                JSONObject jsonObject = (JSONObject) obj;

                JSONArray locals = (JSONArray) jsonObject.get("locals");
                JSONArray routes = (JSONArray) jsonObject.get("routes");
                JSONArray players = (JSONArray) jsonObject.get("Players");

                /*
                ArrayUnorderedList<Local> localList = new ArrayUnorderedList<>();
                for (Object local : locals) {
                    JSONObject localJson = (JSONObject) local;
                    // extract data from localJson and set it in a Local object
                    Local localObj = new Local();
                    // set data in localObj
                    localList.add(localObj);
                }

                ArrayUnorderedList<Route> routeList = new ArrayUnorderedList<>();
                for (Object route : routes) {
                    JSONObject routeJson = (JSONObject) route;
                    // extract data from routeJson and set it in a Route object
                    Route routeObj = new Route();
                    // set data in routeObj
                    routeList.add(routeObj);
                }
                 */

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
            }

        }


}
