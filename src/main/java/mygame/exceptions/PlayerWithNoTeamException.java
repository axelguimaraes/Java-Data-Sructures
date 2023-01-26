package mygame.exceptions;

public class PlayerWithNoTeamException extends Exception{

    public static final String PLAYER_NO_TEAM = "This player has no team";

    public PlayerWithNoTeamException() {
    }

    public PlayerWithNoTeamException(String message) {
        super(message);
    }
}
