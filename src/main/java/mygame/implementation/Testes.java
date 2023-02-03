package mygame.implementation;

import mygame.exceptions.PlayerWithNoTeamException;
import mygame.game.Team;
import mygame.structures.classes.ArrayUnorderedList;
import mygame.game.Player;

//CLASSE PARA TESTES, NADA RELACIONADO AO PROGRAMA
public class Testes {
    public static void main(String[] args) throws PlayerWithNoTeamException {

        ArrayUnorderedList<Player> players = new ArrayUnorderedList<>();
        Player p1 = new Player("JOAO", Team.GIANTS);
        Player p2 = new Player("JOAO", Team.GIANTS);
        Player p3 = new Player("JOAO", Team.GIANTS);
        players.addToFront(p1);
        players.addToFront(p2);
        players.addToFront(p3);


        System.out.println("Hello WORLD");

    }
}
