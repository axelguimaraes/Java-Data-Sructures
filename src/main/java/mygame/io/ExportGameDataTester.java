package mygame.io;

import mygame.exceptions.PlayerWithNoTeamException;
import mygame.game.*;
import mygame.io.ExportGameData;
import mygame.structures.classes.ArrayUnorderedList;

public class ExportGameDataTester {
    public static void main(String[] args) throws PlayerWithNoTeamException {
        Coordinates coords = new Coordinates(10.100, 20.200);
        Coordinates coords2 = new Coordinates(30.100, 40.200);

        Coordinates coordsCon = new Coordinates(1930.100, 340.200);
        Coordinates coordsCon2 = new Coordinates(4310.100, 6420.200);

        Player player = new Player("Joao", Team.SPARKS, 100, 200, 300, 200);
        Player player2 = new Player("Ines", Team.GIANTS, 10, 20, 500, 340);

        Portal portal = new Portal("Ponte D.Luis do Bairro Alto", coords, 200, 300, player);
        Portal portal2 = new Portal("ESTG", coords2, 100, 250, player2);

        Connector connector = new Connector(100, coordsCon, 100);
        Connector connector2 = new Connector(200, coordsCon2, 200);

        ArrayUnorderedList<Player> playersList = new ArrayUnorderedList<>();
        ArrayUnorderedList<Portal> portalList = new ArrayUnorderedList<>();
        ArrayUnorderedList<Connector> connectorList = new ArrayUnorderedList<>();

        playersList.addToRear(player);
        playersList.addToRear(player2);
        portalList.addToRear(portal);
        portalList.addToRear(portal2);
        connectorList.addToRear(connector);
        connectorList.addToRear(connector2);



        ExportGameData egd = new ExportGameData();
        egd.exportToJson(portalList, connectorList, playersList);
    }
}
