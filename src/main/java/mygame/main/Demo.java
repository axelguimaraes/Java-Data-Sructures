package mygame.main;

import mygame.exceptions.GraphExceptions;
import mygame.exceptions.PlayerWithNoTeamException;
import mygame.game.*;
import mygame.io.Input;
import mygame.io.Output;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws PlayerWithNoTeamException {
        Local portal1 = new Portal("Portal", 150, new Coordinates(123123, 123123), null, 200);
        Player player = new Player("Player", Team.GIANTS);

        System.out.println(portal1.getId());
    }
}
