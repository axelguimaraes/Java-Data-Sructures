package mygame.implementation;

import mygame.game.Player;
import mygame.game.Team;

import java.util.Scanner;

public class Menu {
    Player player = new Player("Joao", 100, Team.SPARKS);
    Scanner sc = new Scanner(System.in);

    public void runMenu() {
        System.out.print("\nMENU\n1 - Register New Player\n2 - Login as Player\n3 - Show Players");
        System.out.print("\nOption: ");
        int num = sc.nextInt();
        RegisterPlayer player;
        switch (num) {
            case 1:
                player = new RegisterPlayer();
                player.register();
                break;
            case 2:

                break;
            case 3:
                player = new RegisterPlayer();
                player.showPlayers();
                break;
            default:
                System.out.println("\nInvalid Option\n");
                runMenu();
        }
    }
}
