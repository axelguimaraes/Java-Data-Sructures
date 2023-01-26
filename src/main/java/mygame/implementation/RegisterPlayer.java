package mygame.implementation;
import mygame.game.Player;

import java.sql.SQLOutput;
import java.util.Scanner;
import mygame.game.Team;
import mygame.structures.lists.UnorderedArrayList;

public class RegisterPlayer {
    UnorderedArrayList<Player> players = new UnorderedArrayList<>();
    public void register() {
        String namePlayer = getInputString("Insert Name: ");
        Team teamPlayer = getInputTeam();
        Player player = new Player(namePlayer, 0, teamPlayer);
        players.addToFront(player);
    }

        private String getInputString(String s){
            Scanner sc = new Scanner(System.in);
            System.out.println(s);
            return sc.nextLine();
        }

        private Team getInputTeam(){
            System.out.println("Insert Team: \n1 - SPARKS\n2 - GIANTS\n");
            int team = 0;
            boolean isValid = false;

            while(!isValid){
                try{
                    team = Integer.parseInt(getInputString("Option: "));
                    if (team == 1 || team == 2) {
                        isValid = true;
                    }else {
                        System.out.println("Invalid option, please try again.");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Invalid input, please try again.");
                }

            }
            return team == 1 ? Team.SPARKS : Team.GIANTS;
        }

        public void showPlayers(){
            players.toString();
        }
}
