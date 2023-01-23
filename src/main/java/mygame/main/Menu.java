package mygame.main;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Portal Conquest Game!");
            System.out.println();
            System.out.println("Please select from the following options:");
            System.out.println("1. View map and available routes");
            System.out.println("2. View team information and progress");
            System.out.println("3. Interact with a Portal or Connector");
            System.out.println("4. Recharge energy at a Connector");
            System.out.println("5. View player statistics");
            System.out.println("6. Quit game");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewMap();
                    break;
                case 2:
                    viewTeamInfo();
                    break;
                case 3:
                    interactWithPortalOrConnector();
                    break;
                case 4:
                    rechargeEnergy();
                    break;
                case 5:
                    viewPlayerStatistics();
                    break;
                case 6:
                    quit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void viewMap() {
        // code to display the map and available routes
    }

    private static void viewTeamInfo() {
        // code to display team information and progress
    }

    private static void interactWithPortalOrConnector() {
        // code to interact with a portal or connector
    }

    private static void rechargeEnergy() {
        // code to recharge energy at a connector
    }

    private static void viewPlayerStatistics() {
        // code to display player statistics
    }

    private static void quit() {
        System.out.println("Thanks for playing! Goodbye!");
        System.exit(0);
    }
}