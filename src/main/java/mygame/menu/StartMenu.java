package mygame.menu;

import mygame.exceptions.PlayerNotFoundException;
import mygame.exceptions.PlayerWithNoTeamException;
import mygame.game.*;
import mygame.io.IOGameSettings;
import mygame.structures.classes.ArrayUnorderedList;
import mygame.structures.classes.LinkedQueue;
import mygame.structures.exceptions.ElementNotFoundException;
import mygame.io.InputGameData;
import mygame.io.ExportGameData;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class StartMenu {
    public static void main(String[] args) throws PlayerNotFoundException, PlayerWithNoTeamException {
        GameMap gameMap = new GameMap();
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        GameSettings gameSettings;
        GameMoves gameMoves = null;

        try {
            gameSettings = IOGameSettings.importData();
            gameMoves = new GameMoves(gameSettings);
        } catch (IOException ex) {
            System.err.println("Unable to import game settings!");
            System.exit(1);
        }


        while (true) {
            try {
                System.out.println("== START MENU ==\n\n" +
                        "" +
                        "1. Players menu\n" +
                        "2. Map menu\n" +
                        "5. Import Demo (DEV ONLY)\n\n" + // TODO: DEV ONLY!!!
                        "" +
                        "3. Start\n" +
                        "0. Exit\n\n" +
                        "Your choice:");

                switch (scanner.nextInt()) {
                    case 1:
                        playersMenu(gameMap, scanner);
                        break;
                    case 2:
                        mapMenu(gameMap, scanner);
                        break;
                    case 3:
                        if (gameMap.getPlayersInGame().isEmpty()) {
                            System.err.println("No players registered in the game!");
                        } else {
                            gameStart(gameMap, scanner, gameMoves);
                        }
                        break;
                    case 5:
                        importEverything(gameMap);
                        System.err.println("Imported!");
                        break;
                    case 0:
                        System.out.println("Bye!");
                        System.exit(0);
                    default:
                        System.err.println("Invalid option!");
                }
            } catch (InputMismatchException ex) {
                System.err.println("Invalid option!");
                scanner = new Scanner(System.in).useDelimiter("\\n");
            }
        }
    }

    public static void playersMenu(GameMap gameMap, Scanner scanner) throws PlayerWithNoTeamException, PlayerNotFoundException {
        while (true) {
            System.out.println("== PLAYERS MENU ==\n\n" +
                    "" +
                    "1. Register player\n" +
                    "2. List players\n" +
                    "3. Edit player\n" +
                    "4. Remove player\n" +
                    "5. Import players list\n" +
                    "6. Export players list\n" +
                    "0. Exit\n\n" +
                    "" +
                    "Your choice: ");

            switch (scanner.nextInt()) {
                case 1:
                    if (gameMap.getMap().isEmpty()) {
                        System.err.println("No existing locations to add player!");
                        break;
                    }

                    System.out.println(" == REGISTER PLAYER ==\nName: ");
                    String name = scanner.next();

                    System.out.println("Team (1) Sparks | (2) Giants:");
                    Team team = null;
                    switch (scanner.nextInt()) {
                        case 1:
                            team = Team.SPARKS;
                            break;
                        case 2:
                            team = Team.GIANTS;
                            break;
                        default:
                            System.err.println("Invalid option!");
                    }

                    if (team == null) {
                        System.err.println("Player not added");
                        break;
                    }

                    gameMap.addPlayer(new Player(name, team));
                    System.err.println("Player added!");
                    break;

                case 2:
                    System.out.println("== LIST PLAYERS ==\n");
                    if (gameMap.getPlayersInGame().isEmpty()) {
                        System.err.println("No existing players!");
                        break;
                    }

                    for (Player player : gameMap.getPlayersInGame()) {
                        System.out.println(player.toString() + "\n");
                    }
                    break;

                case 3:
                    if (gameMap.getPlayersInGame().isEmpty()) {
                        System.err.println("No existing players!");
                        break;
                    }
                    System.out.println("== EDIT PLAYER ==\n" +
                            "ID of player to edit:");

                    gameMap.editPlayer(gameMap.getPlayerFromID(scanner.nextInt()));
                    System.err.println("Player edited!");
                    break;

                case 4:
                    if (gameMap.getPlayersInGame().isEmpty()) {
                        System.err.println("No existing players!");
                        break;
                    }
                    System.out.println("== REMOVE PLAYER ==\n" +
                            "ID of player to remove:");

                    gameMap.removePlayer(gameMap.getPlayerFromID(scanner.nextInt()));
                    System.err.println("Player removed!");
                    break;

                case 5:
                    if (gameMap.getMap().isEmpty()) {
                        System.err.println("Can't add players to an empty map!");
                        break;
                    }
                    InputGameData inputClasses = new InputGameData();
                    inputClasses.parseJSON("files/exemploNovo.json");
                    inputClasses.readPlayers(gameMap);
                    // TODO: Import players
                    break;
                case 6:


                    // TODO: Export players
                    break;
                case 0:
                    return;
                default:
                    System.err.println("Invalid option!");
            }
        }
    }

    public static void mapMenu(GameMap gameMap, Scanner scanner) {
        while (true) {
            System.out.println("== MAP MENU ==\n\n" +
                    "" +
                    "1. Locations menu\n" +
                    "2. Import map\n" +
                    "3. Export map\n" +
                    "0. Exit\n\n" +
                    "" +
                    "Your choice: ");

            switch (scanner.nextInt()) {
                case 1:
                    locationsMenu(gameMap, scanner);
                    break;
                case 2:
                    InputGameData inputClasses = new InputGameData();
                    inputClasses.parseJSON("files/exemploNovo.json");
                    inputClasses.readPortals(gameMap);
                    inputClasses.readConnectors(gameMap);
                    System.out.println("Import Data with Success");
                    // TODO: Import map
                    break;
                case 3:
                    ExportGameData exportGameData = new ExportGameData();
                    //exportGameData.mapExportToJson(gameMap.);
                    // TODO: Export map
                    break;
                case 0:
                    return;
                default:
                    System.err.println("Invalid option!");
            }
        }
    }

    public static void gameStart(GameMap gameMap, Scanner scanner, GameMoves gameMoves) throws PlayerNotFoundException {
        LinkedQueue<Integer> playersTurn = new LinkedQueue<>();
        for (Player player : gameMap.getPlayersInGame()) {
            playersTurn.enqueue(player.getId());
        }

        while (true) {
            Player turn = gameMap.getPlayerFromID(playersTurn.dequeue());
            Local currentPosition = gameMap.getLocalByID(turn.getCurrentPositionID());

            System.out.println("\n\n\t== PLAYER " + turn.getId() + " TURN ==\n" +
                    "Player name: " + turn.getName() + "\t| Team: " + turn.getTeam() + "\n" +
                    "Energy: " + turn.getEnergy() + "\n" +
                    "Level: " + turn.getLevel() + "\n" +
                    "XP: " + (int) turn.getXp() + "\n" +
                    "Current location: " + currentPosition.getLocalType() + " ID " + currentPosition.getId());

            switch (currentPosition.getLocalType()) {
                case PORTAL:
                    Portal portal = (Portal) currentPosition;
                    System.out.println("\tTeam: " + portal.getTeam() + "\n" +
                            "\tName: " + portal.getName() + "\n" +
                            "\tEnergy: " + portal.getEnergy());
                    try {
                        System.out.println("\tConqueror: " + portal.getConqueror().getName() + "\n\n");
                    } catch (NullPointerException ex) {
                        System.out.println("\n");
                    }

                    System.out.println("1. Conquer portal\n" +
                            "2. Charge portal\n" +
                            "3. Navigate to other location\n" +
                            "4. Navigate through multiple locations\n" +
                            "0. Exit to main menu\n\n" +
                            "Your choice: ");
                    switch (scanner.nextInt()) {
                        case 1:
                            gameMoves.playerConquerPortal(gameMap.getPlayerFromID(turn.getId()), (Portal) gameMap.getLocalByID(currentPosition.getId()));
                            break;
                        case 2:
                            System.out.println("How much energy?:");
                            gameMoves.playerChargePortal(turn, scanner.nextInt());
                            break;
                        case 3:
                            listLocations(gameMap, turn);
                            System.out.println("Destination ID: ");
                            gameMoves.playerNavigateTo(gameMap.getPlayerFromID(turn.getId()), gameMap.getLocalByID(scanner.nextInt()));
                            break;
                        case 4:
                            listLocations(gameMap, turn);
                            ArrayUnorderedList<Local> locations = new ArrayUnorderedList<>();
                            int opt = 0, size = 0;
                            while (opt != -1) {
                                System.out.println("Destination ID (-1 to finish): ");
                                opt = scanner.nextInt();
                                if (opt != -1) {
                                    try {
                                        gameMap.getLocalByID(opt);
                                        locations.addToRear(gameMap.getLocalByID(opt));
                                    } catch (ElementNotFoundException e) {
                                        System.err.println("Invalid option!");
                                    }
                                }
                            }
                            Local[] list = new Local[locations.size()];
                            for (Local local : locations) {
                                list[size++] = local;
                            }


                            gameMoves.playerNavigateToByMultipleLocations(gameMap.getPlayerFromID(turn.getId()), list);
                            break;

                        case 0:
                            return;
                        default:
                            System.err.println("Invalid option!");
                    }
                    break;

                case CONNECTOR:
                    Connector connector = (Connector) currentPosition;
                    System.out.println("\tEnergy: " + connector.getEnergy() + "\n\n" +
                            "" +
                            "1. Recharge player energy\n" +
                            "2. Navigate to other location\n" +
                            "0. Exit to main menu\n\n" +
                            "Your choice: ");
                    switch (scanner.nextInt()) {
                        case 1:
                            gameMoves.playerRechargeInConnector(gameMap.getPlayerFromID(turn.getId()), (Connector) gameMap.getLocalByID(currentPosition.getId()));
                            break;
                        case 2:
                            listLocations(gameMap, turn);
                            System.out.println("Destination ID: ");
                            gameMoves.playerNavigateTo(gameMap.getPlayerFromID(turn.getId()), gameMap.getLocalByID(scanner.nextInt()));
                            break;
                        case 0:
                            return;
                        default:
                            System.err.println("Invalid option!");
                    }
                    break;
            }
            playersTurn.enqueue(turn.getId());

        }
    }

    public static void locationsMenu(GameMap gameMap, Scanner scanner) {
        while (true) {
            System.out.println("== LOCATIONS MENU ==\n\n" +
                    "" +
                    "1. Add location\n" +
                    "2. List locations\n" +
                    "3. Edit location\n" +
                    "4. Remove location\n" +
                    "5. Add path\n" +
                    "6. Remove path\n" +
                    "7. Import locations\n" +
                    "8. Export locations\n" +
                    "0. Exit\n\n" +
                    "" +
                    "Your choice: ");

            switch (scanner.nextInt()) {
                case 1:
                    boolean done = false;
                    Local local = null;

                    while (!done) {
                        System.out.println("== ADD LOCATION ==\n\n" +
                                "1. New portal\n" +
                                "2. New connector\n" +
                                "0. Exit\n\n" +
                                "Your choice:");

                        switch (scanner.nextInt()) {
                            case 1:
                                System.out.println("Name:");
                                String name = scanner.next();

                                System.out.println("Energy:");
                                int energy = scanner.nextInt();

                                System.out.println("Latitude:");
                                double lat = scanner.nextDouble();

                                System.out.println("Longitude:");
                                double lon = scanner.nextDouble();

                                boolean check = false;
                                int maxEnergy = 0;
                                while (!check) {
                                    System.out.println("Maximum energy:");
                                    maxEnergy = scanner.nextInt();

                                    if (maxEnergy < energy) {
                                        System.err.println("Maximum energy can't be less than current energy!");
                                    } else {
                                        check = true;
                                    }
                                }

                                local = new Portal(name, energy, new Coordinates(lat, lon), maxEnergy);
                                done = true;
                                break;

                            case 2:
                                System.out.println("Energy:");
                                energy = scanner.nextInt();

                                System.out.println("Latitude:");
                                lat = scanner.nextDouble();

                                System.out.println("Longitude:");
                                lon = scanner.nextDouble();

                                System.out.println("Cooldown (minutes):");
                                int cooldown = scanner.nextInt();

                                local = new Connector(energy, new Coordinates(lat, lon), cooldown);
                                done = true;
                                break;

                            case 0:
                                done = true;
                                break;

                            default:
                                System.err.println("Invalid option!");
                        }

                        if (local == null) {
                            System.err.println("Location not added!");
                            break;
                        }

                        System.err.println("Location added!");
                        gameMap.addLocation(local);
                    }

                    break;
                case 2:
                    System.out.println("== LIST LOCATIONS ==\n");
                    if (gameMap.getMap().isEmpty()) {
                        System.err.println("No locations available!");
                        break;
                    }

                    Iterator<Local> it = gameMap.getMap().iteratorBFS(0);
                    while (it.hasNext()) {
                        Local location = it.next();

                        if (location instanceof Portal) {
                            System.out.println(location + "\n");
                        } else if (location instanceof Connector) {
                            System.out.println(location + "\n");
                        }
                    }
                    break;

                case 3:
                    if (gameMap.getMap().isEmpty()) {
                        System.err.println("No locations available!");
                        break;
                    }
                    System.out.println("== EDIT LOCATION ==\n" +
                            "ID of location to edit:");
                    gameMap.editLocation(gameMap.getLocalByID(scanner.nextInt()));
                    System.err.println("Location edited!");
                    break;

                case 4:
                    if (gameMap.getMap().isEmpty()) {
                        System.err.println("No locations available!");
                        break;
                    }
                    System.out.println("== REMOVE LOCATION ==\n" +
                            "ID of location to remove:");
                    gameMap.removeLocation(gameMap.getLocalByID(scanner.nextInt()));
                    System.err.println("Location removed!");
                    break;

                case 5:
                    if (gameMap.getMap().isEmpty()) {
                        System.err.println("No locations available!");
                        break;
                    }
                    int id1, id2;
                    double weight;

                    System.out.println("== ADD PATH ==\n" +
                            "ID of first location:");
                    id1 = scanner.nextInt();

                    System.out.println("ID of second location:");
                    id2 = scanner.nextInt();

                    System.out.println("Distance (0 to calculate automatically):");
                    weight = scanner.nextDouble();

                    if (weight == 0) {
                        Local local1 = (Local) gameMap.getMap().getVertices()[id1 - 1];
                        Local local2 = (Local) gameMap.getMap().getVertices()[id2 - 1];
                        gameMap.connectLocationsWithCoordinates(local1, local2);
                    } else {
                        gameMap.connectLocations(gameMap.getLocalByID(id1), gameMap.getLocalByID(id2), weight);
                    }
                    System.err.println("Path added!");
                    break;

                case 6:
                    if (gameMap.getMap().isEmpty()) {
                        System.err.println("No locations available!");
                        break;
                    }
                    System.out.println("== REMOVE PATH ==\n" +
                            "First location ID:");
                    id1 = scanner.nextInt();

                    System.out.println("Second location ID:");
                    id2 = scanner.nextInt();

                    gameMap.removeConnectingPath(id1, id2);
                    System.err.println("Path removed!");
                    break;
                case 7:

                    // TODO: Import locations
                    break;
                case 8:
                    // TODO: Export locations
                    break;
                case 0:
                    return;
                default:
                    System.err.println("Invalid option!");
            }
        }
    }

    private static void importEverything(GameMap gameMap) throws PlayerWithNoTeamException {
        Portal portal1 = new Portal("Sao Bento Railway Station", 0, new Coordinates(41.14444, -8.61037), 500);
        Portal portal2 = new Portal("Clerigos Tower", 0, new Coordinates(41.14578, -8.61391), 250);
        Portal portal3 = new Portal("Bolsa Palace", 0, new Coordinates(41.14139, -8.61564), 300);
        Portal portal4 = new Portal("Lello Bookstore", 0, new Coordinates(41.14688, -8.61564), 350);

        Connector connector1 = new Connector(100, new Coordinates(41.14053, -8.60969), 2);
        Connector connector2 = new Connector(300, new Coordinates(41.15904, -8.63069), 8);
        Connector connector3 = new Connector(50, new Coordinates(41.15992, -8.65966), 1);
        Connector connector4 = new Connector(150, new Coordinates(41.14069, -8.61012), 5);
        Connector connector5 = new Connector(100, new Coordinates(41.13813, -8.61088), 2);

        gameMap.addLocation(portal1);
        gameMap.addLocation(portal2);
        gameMap.addLocation(portal3);
        gameMap.addLocation(portal4);
        gameMap.addLocation(connector1);
        gameMap.addLocation(connector2);
        gameMap.addLocation(connector3);
        gameMap.addLocation(connector4);
        gameMap.addLocation(connector5);

        gameMap.connectLocationsWithCoordinates(portal1, connector1);
        gameMap.connectLocationsWithCoordinates(portal1, connector5);
        gameMap.connectLocationsWithCoordinates(portal2, connector1);
        gameMap.connectLocationsWithCoordinates(portal2, connector3);
        gameMap.connectLocationsWithCoordinates(portal3, portal4);
        gameMap.connectLocationsWithCoordinates(portal3, connector4);
        gameMap.connectLocationsWithCoordinates(portal4, portal1);
        gameMap.connectLocationsWithCoordinates(portal4, connector4);
        gameMap.connectLocationsWithCoordinates(portal4, connector5);
        gameMap.connectLocationsWithCoordinates(connector1, portal2);
        gameMap.connectLocationsWithCoordinates(connector1, portal4);
        gameMap.connectLocationsWithCoordinates(connector2, portal2);
        gameMap.connectLocationsWithCoordinates(connector2, portal1);
        gameMap.connectLocationsWithCoordinates(connector3, connector2);
        gameMap.connectLocationsWithCoordinates(connector4, connector3);
        gameMap.connectLocationsWithCoordinates(connector4, portal2);
        gameMap.connectLocationsWithCoordinates(connector4, portal3);
        gameMap.connectLocationsWithCoordinates(connector5, portal4);
        gameMap.connectLocationsWithCoordinates(connector5, portal3);

        gameMap.addPlayer(new Player("John Doe", Team.SPARKS));
        gameMap.addPlayer(new Player("Tom", Team.GIANTS));
        gameMap.addPlayer(new Player("Jerry", Team.SPARKS));
        gameMap.addPlayer(new Player("Abe", Team.GIANTS));
    } // DEV

    private static void listLocations(GameMap gameMap, Player player) {
        DecimalFormat df = new DecimalFormat("0.00");
        Iterator<Local> it = gameMap.getMap().iteratorBFS(0);
        while (it.hasNext()) {
            Local location = it.next();

            if (location instanceof Portal) {
                System.out.println("ID: " + location.getId() + "\tName: " + ((Portal) location).getName() + "\t\tDistance: " + df.format(gameMap.getShortestPathWeight(player.getCurrentPositionID(), location.getId())) + "km");
            } else {
                System.out.println("ID: " + location.getId() + "\tName: Connector " + location.getId() + "\t\tDistance: " + df.format(gameMap.getShortestPathWeight(player.getCurrentPositionID(), location.getId())) + "km");
            }
        }
    }
}
