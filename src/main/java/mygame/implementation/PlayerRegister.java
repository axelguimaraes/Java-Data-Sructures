package mygame.implementation;

import com.google.gson.Gson;
import mygame.exceptions.PlayerWithNoTeamException;
import mygame.game.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import mygame.game.Team;
import mygame.structures.classes.ArrayUnorderedList;

public class PlayerRegister {
    ArrayUnorderedList<Player> playersArray = new ArrayUnorderedList<>();
    Player p1 = new Player("Jaoao", Team.GIANTS);
    public ArrayUnorderedList<Player> getPlayers() {
        return playersArray;
    }
    /**
     *
     * @throws PlayerWithNoTeamException
     */
    public PlayerRegister() throws PlayerWithNoTeamException {
    }

    /**
     * Method that register a new Player
     *
     * @throws PlayerWithNoTeamException
     */
    public void register() throws PlayerWithNoTeamException {
        //Gson gson = new Gson();
        String namePlayer = getInputString("Insert Name: ");
        Team teamPlayer = getInputTeam();
        Player player = new Player(namePlayer,teamPlayer);
        playersArray.addToFront(player);
        //System.out.println(playersArray.toString());
    }

    /**
     * Method to get the name of the Player from himself
     *
     * @param s Name of the Player
     * @return Name of the Player inserted
     */
    private String getInputString(String s) {
        Scanner sc = new Scanner(System.in);
        System.out.print(s);
        return sc.nextLine();
    }

    /**
     * Method to get the team of the Player from himself
     *
     * @return The Team the Player has chosen
     */
    private Team getInputTeam() {
        System.out.print("Insert Team: \n1 - SPARKS\n2 - GIANTS\n");
        int team = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                team = Integer.parseInt(getInputString("Option: "));
                if (team == 1 || team == 2) {
                    isValid = true;
                } else {
                    System.out.println("Invalid option, please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again.");
            }

        }
        return team == 1 ? Team.SPARKS : Team.GIANTS;
    }

    /**
     * Method that give the Player from the chosen ID
     *
     * @param playerSelected chosen ID from all the IDs
     * @return The Player that has the given number of the ID
     */
    public Player givePlayerID(int playerSelected){
        int playerID = 0;
        for (Player player : playersArray){
            if(playerSelected == player.getId()){
                return player;
            }
        }
        return null;
    }

    /**
     * Method to show all the Players registed
     */
    public void showPlayers() {
        for (Player player : playersArray) {
            System.out.println(player.toString2());
            System.out.print("ID Player: " + player.getId());
        }
    }



}
