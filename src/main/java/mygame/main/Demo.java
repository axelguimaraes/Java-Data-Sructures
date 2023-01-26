package mygame.main;

import mygame.exceptions.GraphExceptions;
import mygame.exceptions.ListExceptions;
import mygame.exceptions.LocalNotFoundException;
import mygame.exceptions.PlayerWithNoTeamException;
import mygame.game.*;
import mygame.io.Input;
import mygame.io.Output;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws GraphExceptions, LocalNotFoundException, PlayerWithNoTeamException {
        GameMap gameMap = new GameMap();
        Portal portal1 = new Portal("Portal 1", 150, new Coordinates(123, 123), null, 150);
        Portal portal2 = new Portal("Portal 2", 150, new Coordinates(123, 123), null, 150);

        gameMap.addLocation(portal1);
        gameMap.addLocation(portal2);

        gameMap.connectLocations(portal1, portal2, 10);

        System.out.println(gameMap);

        gameMap.addPlayer(new Player("Player", Team.GIANTS));

        System.out.println(gameMap.getPlayersInGame());
    }
}
