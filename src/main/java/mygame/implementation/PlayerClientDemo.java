package mygame.implementation;

import mygame.game.Player;
import mygame.game.Team;

import java.util.Scanner;

import static java.lang.System.exit;

public class PlayerClientDemo {

    Scanner sc = new Scanner(System.in);

    public void runMenu() {
        //Player p1 = new Player("JOAO", 0, Team.GIANTS);
        //Player p2 = new Player("JOAO", 0, Team.GIANTS);
        //Player p3 = new Player("JOAO", 0, Team.GIANTS);

        PlayerRegister player = new PlayerRegister();
        while (1 == 1) {
            System.out.print("\nMENU\n1 - Register New Player\n2 - Login as Player\n3 - Show Players");
            System.out.print("\nOption: ");
            int num = sc.nextInt();
            switch (num) {
                case 0:
                    /*TODO: Save data*/
                    exit(0);
                case 1:
                    player.register();
                    break;
                case 2:
                    if (isEmpty(player)) System.out.println("No players registed"); 
                    else player.showPlayers();

                    //int playerOption = sc.nextInt();
                    System.out.print("Option: ");
                    /**/
                    break;
                case 3:
                    if (!isEmpty(player)) player.showPlayers();
                    else System.out.println("No players registed");
                    break;
                default:
                    System.out.println("\nInvalid Option\n");
                    runMenu();
            }
        }
    }

    public boolean isEmpty(PlayerRegister player){
        if (player.playersArray.size() == 0)
            return true;
        else return false;
    }
}
