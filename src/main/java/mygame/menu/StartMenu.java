package mygame.menu;

import mygame.game.GameMap;
import mygame.game.Player;
import mygame.structures.classes.LinkedQueue;

import java.util.Scanner;

public class StartMenu {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("== START MENU ==\n\n" +
                    "" +
                    "1. Players menu\n" +
                    "2. Map menu\n\n" +
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
                    gameStart(gameMap, scanner);
                    break;
                case 0:
                    System.out.println("Bye!");
                    System.exit(0);
                default:
                    System.err.println("Invalid option!");
            }
        }
    }

    public static void playersMenu(GameMap gameMap, Scanner scanner) {
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
                    // TODO: Register player
                    break;
                case 2:
                    // TODO: List players
                    break;
                case 3:
                    // TODO: Edit players
                    break;
                case 4:
                    // TODO: Remove players
                    break;
                case 5:
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
                    // TODO: Import map
                    break;
                case 3:
                    // TODO: Export map
                    break;
                case 0:
                    return;
                default:
                    System.err.println("Invalid option!");
            }
        }
    }

    public static void gameStart(GameMap gameMap, Scanner scanner) {
        LinkedQueue<Player> playersTurn = new LinkedQueue<>();
        for (Player player : gameMap.getPlayersInGame()) {
            playersTurn.enqueue(player);
        }
        

        while (true) {

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
                    "8. Export locations\n;" +
                    "0. Exit\n\n" +
                    "" +
                    "Your choice: ");

            switch (scanner.nextInt()) {
                case 1:
                    // TODO: Add location
                    break;
                case 2:
                    // TODO: List locations
                    break;
                case 3:
                    // TODO: Edit location
                    break;
                case 4:
                    // TODO: Remove location
                    break;
                case 5:
                    // TODO: Add path
                    break;
                case 6:
                    // TODO: Remove path
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
}
