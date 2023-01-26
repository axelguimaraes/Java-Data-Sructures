package mygame.main;

import mygame.exceptions.GraphExceptions;
import mygame.exceptions.ListExceptions;
import mygame.exceptions.PlayerWithNoTeamException;
import mygame.game.*;
import mygame.io.Input;
import mygame.io.Output;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws PlayerWithNoTeamException, ListExceptions, InterruptedException {
        Connector connector = new Connector(300, new Coordinates(123123, 123123), 1);
        Player player = new Player("Player", Team.GIANTS);
        player.setEnergy(300);

        Portal portal = new Portal("Portal", 150, new Coordinates(123, 123) , player, 300);
        portal.setTeam(Team.GIANTS);

        player.chargePortal(portal, 10);
        System.out.println(portal);
        System.out.println(player);
    }
}
