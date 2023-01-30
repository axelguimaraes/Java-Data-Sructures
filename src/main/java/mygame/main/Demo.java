package mygame.main;

import mygame.exceptions.*;
import mygame.game.*;
import mygame.io.Input;
import mygame.io.Output;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws GraphExceptions, LocalNotFoundException, PlayerWithNoTeamException, EmptyCollectionException {
        GameMap gameMap = new GameMap();
        Portal portal1 = new Portal("Sao Bento Railway Station", 0, new Coordinates(41.14444, -8.61037), null, 500);
    }
}
