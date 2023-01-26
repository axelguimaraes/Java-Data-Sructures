package mygame.implementation;

import mygame.game.Team;
import mygame.structures.lists.UnorderedArrayList;
import mygame.game.Player;

//CLASSE PARA TESTES, NADA RELACIONADO AO PROGRAMA
public class Testes {
    public static void main(String[] args) {

        UnorderedArrayList<Player> players = new UnorderedArrayList<>();
        Player p1 = new Player("JOAO",0, Team.GIANTS);
        Player p2 = new Player("JOAO",0, Team.GIANTS);
        Player p3 = new Player("JOAO",0, Team.GIANTS);
        players.addToFront(p1);
        players.addToFront(p2);
        players.addToFront(p3);


        System.out.println("Hello WORLD");

    }
}
