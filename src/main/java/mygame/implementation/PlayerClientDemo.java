package mygame.implementation;

import mygame.exceptions.PlayerWithNoTeamException;
import mygame.game.Player;
import mygame.game.Team;

import java.util.Scanner;

import static java.lang.System.exit;

public class PlayerClientDemo {

    Scanner sc = new Scanner(System.in);

    /**
     * Method to run the Player Menu
     *
     * @throws PlayerWithNoTeamException throws exception if the Player has no Team associated
     */
    public void runMenu() throws PlayerWithNoTeamException {
        //Player p1 = new Player("JOAO", 0, Team.GIANTS);
        //Player p2 = new Player("JOAO", 0, Team.GIANTS);
        //Player p3 = new Player("JOAO", 0, Team.GIANTS);

        PlayerRegister player = new PlayerRegister();
        PlayerSelected playerS= new PlayerSelected();
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
                    if (isEmpty(player)){
                        System.out.println("No players registed");
                    }else{
                        player.showPlayers();
                        System.out.print("Option: ");
                        int playerSelected = sc.nextInt();
                        Player ps = player.givePlayerID(playerSelected);
                        playerS.playerActions(ps);
                    }
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

    /**
     * Method to check is the array of Player is Empty or not
     *
     * @param player Object from the class PlayerRegister
     * @return boolean, true if the array is empty or false if the array has at least 1 Player
     */
    public boolean isEmpty(PlayerRegister player){
        if (player.playersArray.size() == 0)
            return true;
        else return false;
    }
}
