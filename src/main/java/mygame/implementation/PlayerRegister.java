package mygame.implementation;

import com.google.gson.Gson;
import mygame.game.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import mygame.game.Team;
import mygame.structures.lists.UnorderedArrayList;

public class PlayerRegister {
    UnorderedArrayList<Player> playersArray = new UnorderedArrayList<>();
    Player p1 = new Player("Jaoao", 0, Team.GIANTS);

    public void register(){
        Gson gson = new Gson();
        String namePlayer = getInputString("Insert Name: ");
        Team teamPlayer = getInputTeam();
        Player player = new Player(namePlayer, 0, teamPlayer);
        playersArray.addToFront(player);
        //System.out.println(playersArray.toString());
    }

    private String getInputString(String s) {
        Scanner sc = new Scanner(System.in);
        System.out.print(s);
        return sc.nextLine();
    }

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

    public void showPlayers() {
        for (Player player : playersArray) {
            System.out.println(player.toString());
        }
    }

}
