package mygame.game;

import mygame.exceptions.PlayerNotFoundException;
import mygame.exceptions.PlayerWithNoTeamException;
import mygame.structures.classes.ArrayUnorderedList;
import mygame.structures.classes.Network;
import mygame.structures.exceptions.ElementNotFoundException;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Class that represents a {@link GameMap}
 */
public class GameMap {
    private final Network<Local> map; // TODO: this is not directed atm
    private final ArrayUnorderedList<Player> playersInGame;

    /**
     * Constructor for the {@link GameMap}. It creates an instance of {@link Network} to represent the game map as a
     * weighted directed graph. It also creates an instance of {@link ArrayUnorderedList} to list all players in-game
     */
    public GameMap() {
        this.map = new Network<>();
        this.playersInGame = new ArrayUnorderedList<>();
    }

    /**
     * Getter for the {@link Network map}
     * @return {@link Network}
     */
    public Network<Local> getMap() {
        return this.map;
    }

    /**
     * Adds a new {@link Local location} to the {@link GameMap}
     * @param local location to add
     */
    public void addLocation(Local local) {
        this.map.addVertex(local);
    }

    /**
     * Removes an existing {@link Local location} from the {@link GameMap}
     * @param local location to be removed
     */
    public void removeLocation(Local local) {
        this.map.removeVertex(local);
    }

    /**
     * Edits an existing {@link Local location} of the {@link GameMap}. It prompts the user with questions on the
     * terminal, so that the user is able to choose which parameters to edit
     * @param local location to be edited
     */
    public void editLocation(Local local) {
        Iterator<Local> it = this.map.iteratorBFS(0);
        Scanner scanner = new Scanner(System.in);
        while (it.hasNext()) {
            Local itLocal = it.next();
            if (itLocal.equals(local)) {
                if (itLocal instanceof Portal) {
                    System.out.println("Change name? (y/n):");
                    switch (scanner.nextLine()) {
                        case "Y":
                        case "y":
                            System.out.println("Name:");
                            ((Portal) itLocal).setName(scanner.nextLine());
                    }

                    System.out.println("Change team? (y/n):");
                    switch (scanner.nextLine()) {
                        case "y":
                        case "Y":
                            boolean check = false;
                            while (!check) {
                                System.out.println("(1) Sparks | (2) Giants | (3) None:");
                                switch (scanner.nextInt()) {
                                    case 1:
                                        ((Portal) itLocal).setTeam(Team.SPARKS);
                                        check = true;
                                        break;
                                    case 2:
                                        ((Portal) itLocal).setTeam(Team.GIANTS);
                                        check = true;
                                        break;
                                    case 3:
                                        ((Portal) itLocal).setTeam(Team.NONE);
                                        check = true;
                                        break;
                                    default:
                                        System.err.println("Invalid option!");
                                }
                            }
                    }

                    System.out.println("Change conqueror? (portal team will change to player's team if different) (y/n):");
                    switch (scanner.nextLine()) {
                        case "y":
                        case "Y":
                            System.out.println("Player ID:");
                            int id = scanner.nextInt();
                            boolean found = false;
                            for (Player player : this.playersInGame) {
                                if (player.getId() == id) {
                                    found = true;
                                    ((Portal) itLocal).getConqueror().removeFromConqueredPortalsList(itLocal.getId());
                                    ((Portal) itLocal).setConqueror(player);
                                    ((Portal) itLocal).setTeam(player.getTeam());
                                    ((Portal) itLocal).getConqueror().addToConqueredPortalsList(itLocal.getId());
                                    break;
                                }
                            }

                            if (!found) {
                                System.err.println("Player not found!");
                            }
                    }

                    System.out.println("Change energy? (y/n)");
                    switch (scanner.nextLine()) {
                        case "y":
                        case "Y":
                            System.out.println("Energy:");
                            itLocal.setEnergy(scanner.nextInt());
                    }

                    System.out.println("Change coordinates? (y/n):");
                    switch (scanner.nextLine()) {
                        case "y":
                        case "Y":
                            double lat, lon;
                            System.out.println("Latitude:");
                            lat = scanner.nextLong();
                            System.out.println("Longitude:");
                            lon = scanner.nextLong();

                            itLocal.setCoordinates(new Coordinates(lat, lon));
                    }
                } else if (itLocal instanceof Connector) {
                    System.out.println("Change cooldown? (y/n):");
                    switch (scanner.nextLine()) {
                        case "y":
                        case "Y":
                            System.out.println("Cooldown:");
                            ((Connector) itLocal).setCooldown(scanner.nextInt());
                    }

                    System.out.println("Change energy? (y/n):");
                    switch (scanner.nextLine()) {
                        case "Y":
                        case "y":
                            System.out.println("Energy");
                            itLocal.setEnergy(scanner.nextInt());
                    }
                    System.out.println("Change coordinates? (y/n):");
                    switch (scanner.nextLine()) {
                        case "y":
                        case "Y":
                            double lat, lon;
                            System.out.println("Latitude:");
                            lat = scanner.nextLong();
                            System.out.println("Longitude:");
                            lon = scanner.nextLong();

                            itLocal.setCoordinates(new Coordinates(lat, lon));
                    }
                }
            }
        }
    }

    /**
     * TODO: sorting here
     */
    public void listLocations() { // TODO: searchAndSort
    }

    /**
     * Adds a {@link Player} to the {@link GameMap}
     * @param player player to be added
     */
    public void addPlayer(Player player) {
        player.setMap(this);
        player.setCurrentPositionID(1);
        playersInGame.addToRear(player);
    }

    /**
     * Edits an existing {@link Player} on the {@link GameMap}
     * @param player player to be edited
     */
    public void editPlayer(Player player) {
        if (!this.playersInGame.contains(player)) {
            throw new ElementNotFoundException("players list");
        }

        System.out.println("Player current data:\n" + player.toString());

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

    /**
     * Removes an existing {@link Player} from the {@link GameMap}
     * @param player player to be removed
     */
    public void removePlayer(Player player) {
        this.playersInGame.remove(player);
    }

    /**
     * Getter for the list of {@link Player players} in-game
     * @return {@link ArrayUnorderedList list} of {@link Player players} in-game
     */
    public ArrayUnorderedList<Player> getPlayersInGame() {
        return this.playersInGame;
    }

    public Player getPlayerFromID (int id) throws PlayerNotFoundException {
        for (Player player : this.playersInGame) {
            if (player.getId() == id) {
                return player;
            }
        }
        throw new PlayerNotFoundException();
    }

    /**
     * Fetches an existing {@link Local location} from the {@link GameMap} by its ID
     * @param id identification number of the {@link Local location}
     * @return {@link Local location} to be fetched
     */
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

    /**
     * Gets the shortest path between two {@link Local locations}
     * @param startID {@link Local location} of origin
     * @param targetID {@link Local location} of destination
     * @return {@link ArrayUnorderedList list} of {@link Local locations} to go through
     */
    public ArrayUnorderedList<Local> getShortestPathToLocal(int startID, int targetID) {
        ArrayUnorderedList<Local> list = new ArrayUnorderedList<>();
        Iterator<Local> it = getIteratorShortestPath(startID, targetID);
        while (it.hasNext()) {
            list.addToRear(it.next());
        }
        return list;
    }

    /**
     * Gets the shortest path between multiple {@link Local locations}. Best used when it's required to calculate the
     * shortest path, whilst forcefully passing through specific {@link Local locations}
     * @param localIDs {@link Local locations to be included on the path. Origin and destination included}
     * @return {@link ArrayUnorderedList list} of {@link Local locations} to go through
     */
    public ArrayUnorderedList<Local> getShortestPathBetweenMultipleLocals(int... localIDs) {
        ArrayUnorderedList<Local> list = new ArrayUnorderedList<>();
        if (localIDs.length == 0) {
            throw new IllegalArgumentException("Method must have 1 or more arguments!");
        }

        for (int i = 0; i + 1 < localIDs.length; i++) {
            Iterator<Local> it = getIteratorShortestPath(localIDs[i], localIDs[i + 1]);
            while (it.hasNext()) {
                list.addToRear(it.next());
            }
        }

        return list;
    }

    /**
     * Connects two existing {@link Local locations} on the {@link GameMap}
     * @param location1 first {@link Local location}
     * @param location2 second {@link Local location}
     * @param weight path distance of the {@link Local locations} to connect
     */
    public void connectLocations(Local location1, Local location2, double weight) {
        this.map.addEdge(location1, location2, weight);
    }

    /**
     * Connects two existing {@link Local locations} on the {@link GameMap}. The method automatically adds the path
     * distance, which is calculated using the {@link Local locations's} {@link Coordinates coordinates}
     * @param location1 first {@link Local location}
     * @param location2 second {@link Local location}
     */
    public void connectLocationsWithCoordinates(Local location1, Local location2) {
        this.map.addEdge(location1, location2, coordinatesDistance(location1, location2));
    }

    /**
     * Removes a connection path between two {@link Local locations}
     * @param firstLocalID first {@link Local location}
     * @param secondLocalID second {@link Local location}
     */
    public void removeConnectingPath(int firstLocalID, int secondLocalID) {
        this.map.removeEdge(getLocalByID(firstLocalID), getLocalByID(secondLocalID));
    }

    /**
     * Lists all the information relative to the {@link GameMap}
     * @return {@link String} of all the information
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.map);

        stringBuilder.append("\nPlayers in-game:\n");
        if (this.playersInGame.size() > 0) {
            for (Player player : playersInGame) {
                stringBuilder.append(player.getName()).append("\n");
            }
        } else {
            stringBuilder.append("No players added\n");
        }

        return stringBuilder.toString();
    }

    /**
     * Calculates the distance between two {@link Local locations} using their {@link Coordinates coordinates}
     * @param local1 first {@link Local location}
     * @param local2 second {@link Local location}
     * @return path distance
     */
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

    /**
     * Gets an iterator of the shortest path between two {@link Local locations} using their IDs
     * @param start ID of the {@link Local location} of origin
     * @param target ID of the {@link Local location} of destination
     * @return {@link Iterator} of the shortest path
     */
    private Iterator<Local> getIteratorShortestPath(int start, int target) {
        return this.map.iteratorShortestPath(getLocalByID(start), getLocalByID(target));
    }

    /**
     * Gets the shortest path weight between two {@link Local locations} using their IDs
     * @param start ID of the {@link Local location} of origin
     * @param target ID of the {@link Local location} of destination
     * @return shortest path weight
     */
    public double getShortestPathWeight(int start, int target) {
        return this.map.shortestPathWeight(getLocalByID(start), getLocalByID(target));
    }

    /**
     * Gets the shortest path weight between multiple {@link Local locations} using their IDs. Best used when calculating
     * the shortest path between two {@link Local locations}, whilst forcefully passing throw specific {@link Local locations}
     * @param locals {@link Local locations} to pass through
     * @return shortest path weight
     */
    public double getShortestPathWeightBetweenMultipleLocals(int... locals) {
        if (locals.length == 0) {
            throw new IllegalArgumentException("Method must have 1 or more arguments!");
        }

        double weight = 0;
        for (int i = 0; i + 1 < locals.length; i++) {
            weight += this.map.shortestPathWeight(getLocalByID(locals[i]), getLocalByID(locals[i + 1]));
        }

        return weight;
    }
}
