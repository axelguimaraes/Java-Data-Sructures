package mygame.main;

import mygame.exceptions.GraphExceptions;
import mygame.game.*;
import mygame.io.Input;
import mygame.io.Output;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws GraphExceptions, IOException {
        Portal portal1 = new Portal("Memorial fountain",150, new Coordinates(14253, 142453), null, 100);
        Portal portal2 = new Portal("Baseball field",150, new Coordinates(14253, 142453), null, 100);
        Portal portal3 = new Portal("Ball field",150, new Coordinates(14253, 142453), null, 100);

        Connector connector1 = new Connector(2134, new Coordinates(141234, 1242134), 10);
        Connector connector2 = new Connector(2134, new Coordinates(141234, 1242134), 14);
        Connector connector3 = new Connector(2134, new Coordinates(141234, 1242134), 12);

        GameMap<Local> map = new GameMap<>();
        map.addLocation(portal1);
        map.addLocation(portal2);
        map.addLocation(portal3);

        map.addLocation(connector1);
        map.addLocation(connector2);
        map.addLocation(connector3);

        map.connectLocations(portal1, connector1, 5);
        map.connectLocations(connector2, connector1, 10);
        map.connectLocations(portal2, connector2, 7);
        map.connectLocations(connector2, connector3, 4);
        map.connectLocations(portal3, connector3, 8);

        System.out.println(map);

        Output.exportGameMap(map);

        GameMap newMap = Input.importGameMap();
        System.out.println(newMap);

    }
}
