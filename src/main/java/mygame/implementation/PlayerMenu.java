package mygame.implementation;

import mygame.exceptions.PlayerWithNoTeamException;

public class PlayerMenu {
    public static void main(String[] args) throws PlayerWithNoTeamException {
        PlayerClientDemo playerClientDemo = new PlayerClientDemo();
        playerClientDemo.runMenu();
    }
}
