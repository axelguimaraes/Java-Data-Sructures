package mygame.main;

import mygame.exceptions.*;
import mygame.game.*;
import mygame.implementation.PlayerRegister;
import mygame.io.Input;
import mygame.io.Output;

import javax.sound.sampled.Port;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws GraphExceptions, LocalNotFoundException, PlayerWithNoTeamException, EmptyCollectionException, IOException {
        GameMap gameMap = new GameMap();

        Portal portal1 = new Portal("Sao Bento Railway Station", 0, new Coordinates(41.14444, -8.61037), null, 500);
        Portal portal2 = new Portal("Clerigos Tower", 0, new Coordinates(41.14578, -8.61391), null, 250);
        Portal portal3 = new Portal("Bolsa Palace", 0, new Coordinates(41.14139, -8.61564), null, 300);
        Portal portal4 = new Portal("Lello Bookstore", 0, new Coordinates(41.14688, -8.61564), null, 350);

        Connector connector1 = new Connector(100, new Coordinates(41.14053, -8.60969), 2);
        Connector connector2 = new Connector(300, new Coordinates(41.15904, -8.63069), 8);
        Connector connector3 = new Connector(50, new Coordinates(41.15992, -8.65966), 1);
        Connector connector4 = new Connector(150, new Coordinates(41.14069, -8.61012), 5);
        Connector connector5 = new Connector(100, new Coordinates(41.13813, -8.61088), 2);

        gameMap.addLocation(portal1);
        gameMap.addLocation(portal2);
        gameMap.addLocation(portal3);
        gameMap.addLocation(portal4);
        gameMap.addLocation(connector1);
        gameMap.addLocation(connector2);
        gameMap.addLocation(connector3);
        gameMap.addLocation(connector4);
        gameMap.addLocation(connector5);

        gameMap.connectLocationsWithCoordinates(portal1, connector1);
        gameMap.connectLocationsWithCoordinates(portal1, connector5);
        gameMap.connectLocationsWithCoordinates(portal2, connector1);
        gameMap.connectLocationsWithCoordinates(portal2, connector3);
        gameMap.connectLocationsWithCoordinates(portal3, portal4);
        gameMap.connectLocationsWithCoordinates(portal3, connector4);
        gameMap.connectLocationsWithCoordinates(portal4, portal1);
        gameMap.connectLocationsWithCoordinates(portal4, connector4);
        gameMap.connectLocationsWithCoordinates(portal4, connector5);
        gameMap.connectLocationsWithCoordinates(connector1, portal2);
        gameMap.connectLocationsWithCoordinates(connector1, portal4);
        gameMap.connectLocationsWithCoordinates(connector2, portal2);
        gameMap.connectLocationsWithCoordinates(connector2, portal1);
        gameMap.connectLocationsWithCoordinates(connector3, connector2);
        gameMap.connectLocationsWithCoordinates(connector4, connector3);
        gameMap.connectLocationsWithCoordinates(connector4, portal2);
        gameMap.connectLocationsWithCoordinates(connector4, portal3);
        gameMap.connectLocationsWithCoordinates(connector5, portal4);
        gameMap.connectLocationsWithCoordinates(connector5, portal3);

        //System.out.println(gameMap);

        //Output.exportGameMap(gameMap);
        //GameMap newMap = Input.importGameMap();
        //Connector connector = Input.importConnector();
        //Portal newPortal = Input.importPortal();

        Input importer = new Input();
        PlayerRegister players = importer.importData("players.json");
        for (Player player : players.getPlayers()) {
            System.out.println("Name: " + player.getName());
            System.out.println("Team: " + player.getTeam());
            System.out.println("Level: " + player.getLevel());
            System.out.println("Experience Points: " + player.getXp());
            //System.out.println("Max Energy: " + player.getMaxEnergy());
            System.out.println("Current Energy: " + player.getEnergy());
        }
    }
}
