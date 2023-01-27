package mygame.implementation;
import mygame.exceptions.PlayerWithNoTeamException;
import mygame.game.Player;


public class PlayerSelected {
    public void playerActions(Integer playerID){
        System.out.println("Hello " + player.getName());
    }

    public Player getPlayerByID(Integer playerID) throws PlayerWithNoTeamException {
        for (Player player : players) {
            if (player.getId().equals(playerID)) {
                return player;
            }
        }
        return null;
    }
}
