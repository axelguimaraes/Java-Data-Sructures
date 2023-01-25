package mygame.main;

import mygame.game.Player;
import mygame.game.PlayerInteraction;
import mygame.game.Team;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Player player = new Player(12312, Team.GIANTS);
        PlayerInteraction interaction = new PlayerInteraction(player, 2);

        Thread.sleep(5000);

        System.out.println(interaction.getElapsedTime());
        System.out.println(interaction.isCooldownOver());
    }
}
