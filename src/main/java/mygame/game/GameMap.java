package mygame.game;

import mygame.exceptions.PlayerWithNoTeamException;
import mygame.structures.classes.ArrayUnorderedList;
import mygame.structures.classes.Network;
import mygame.structures.exceptions.ElementNotFoundException;

import java.util.Iterator;
import java.util.Scanner;

public class GameMap {
    private final Network<Local> map;
    private final ArrayUnorderedList<Player> playersInGame;

    public GameMap() {
        this.map = new Network<>();
        this.playersInGame = new ArrayUnorderedList<>();
    }

    public void addLocation(Local local) {
        this.map.addVertex(local);
    }

    public void addPlayer(Player player) {
        player.setMap(this);
        player.setCurrentPositionID(1);
        playersInGame.addToRear(player);
    }

    public void editPlayer(Player player) throws PlayerWithNoTeamException {
        if (!this.playersInGame.contains(player)) {
            throw new ElementNotFoundException("players list");
        }
        Scanner scanner = new Scanner(System.in);
        int team;
        for (int i = 0; i < this.playersInGame.size(); i++) {
            if (this.playersInGame.get(i).equals(player)) {

                System.out.println("Change name? (y/n): ");
                switch (scanner.nextLine()) {
                    case "y":
                    case "Y":
                        System.out.println("Player name: ");
                        this.playersInGame.get(i).setName(scanner.nextLine());
                }

                System.out.println("Change team? (y/n): ");
                switch (scanner.nextLine()) {
                    case "y":
                    case "Y":
                        boolean check = false;
                        while (!check) {
                            System.out.println("Team: (1) Sparks | (2) Giants: ");
                            team = scanner.nextInt();
                            switch (team) {
                                case 1:
                                    this.playersInGame.get(i).setTeam(Team.SPARKS);
                                    check = true;
                                    break;
                                case 2:
                                    this.playersInGame.get(i).setTeam(Team.GIANTS);
                                    check = true;
                                    break;
                                default:
                                    System.err.println("Invalid choice!");
                            }
                        }
                }

                System.out.println("Change energy? (y/n):");
                switch (scanner.nextLine()) {
                    case "Y":
                    case "y":
                        System.out.println("Energy: ");
                        this.playersInGame.get(i).setEnergy(scanner.nextInt());
                }

                System.out.println("Change level? (y/n):");
                switch (scanner.nextLine()) {
                    case "y":
                    case "Y":
                        System.out.println("Level:");
                        this.playersInGame.get(i).setLevel(scanner.nextInt());
                }

                System.out.println("Change current location? (y/n):");
                switch (scanner.nextLine()) {
                    case "y":
                    case "Y":
                        System.out.println("Location ID:");
                        int id = scanner.nextInt();
                        if (getLocalByID(id) != null) {
                            this.playersInGame.get(i).setCurrentPositionID(id);
                        } else {
                            System.err.println("No such location!");
                        }
                }
                return;
            }
        }
    }

    public void removePlayer(Player player) {
        this.playersInGame.remove(player);
    }

    public ArrayUnorderedList<Player> getPlayersInGame() {
        return this.playersInGame;
    }

    public Local getLocalByID(int id) {
        Iterator<Local> iterator = this.map.iteratorDFS(0);
        while (iterator.hasNext()) {
            Local local = iterator.next();
            if (local.getId() == id) {
                return local;
            }
        }
        throw new ElementNotFoundException("network");
    }

    public ArrayUnorderedList<Local> getShortestPathToLocal(int startID, int targetID) {
        ArrayUnorderedList<Local> list = new ArrayUnorderedList<>();
        Iterator<Local> it = getIteratorShortestPath(getLocalByID(startID), getLocalByID(targetID));
        while (it.hasNext()) {
            list.addToRear(it.next());
        }
        return list;
    }

    public void connectLocations(Local location1, Local location2, double weight) {
        this.map.addEdge(location1, location2, weight);
    }

    public void connectLocationsWithCoordinates(Local location1, Local location2) {
        this.map.addEdge(location1, location2, coordinatesDistance(location1, location2));
    }

    public String toString() {
        return this.map.toString();
    }

    private static double coordinatesDistance(Local local1, Local local2) {
        final int EARTH_RADIUS = 6371;
        double lat1 = local1.getCoordinates().getLatitude();
        double lat2 = local2.getCoordinates().getLatitude();
        double lon1 = local1.getCoordinates().getLongitude();
        double lon2 = local2.getCoordinates().getLongitude();

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    private Iterator<Local> getIteratorShortestPath(Local start, Local target) {
        return this.map.iteratorShortestPath(start, target);
    }

    public double getShortestPathweight(Local start, Local target) {
        return this.map.shortestPathWeight(start, target);
    }
}
