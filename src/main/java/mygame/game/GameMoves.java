package mygame.game;

import static java.lang.Math.pow;

/**
 * Class that represents a {@link Player player}'s possible moves in the game
 */
public class GameMoves {
    private final GameSettings gameSettings;

    /**
     * Constructor for the {@link GameMoves} class
     *
     * @param gameSettings {@link GameSettings} class that contains all global game settings
     */
    public GameMoves(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    /**
     * Conquers a portal
     *
     * @param player {@link Player} to conquer
     * @param portal {@link Portal} to be conquered
     */
    public void playerConquerPortal(Player player, Portal portal) {
        if (player.conquerPortal(portal)) {
            player.addXp(this.gameSettings.getPortalConquestPoints());
            player.setLevel(levelUp(player));
        }
    }

    /**
     * Charges a {@link Portal}
     *
     * @param player {@link Player} to charge the {@link Portal}
     * @param energy ammount of energy to charge
     */
    public void playerChargePortal(Player player, int energy) {
        if (player.chargePortal(energy)) {
            player.addXp(this.gameSettings.getPortalChargingPoints());
            player.setLevel(levelUp(player));
        }
    }

    /**
     * Recharges a {@link Player} in a {@link Connector}
     *
     * @param player    {@link Player} to be charged
     * @param connector {@link Connector} to charge
     */
    public void playerRechargeInConnector(Player player, Connector connector) {
        if (player.rechargeEnergy(connector)) {
            player.addXp(this.gameSettings.getConnectorPlayerChargingPoints());
            player.setLevel(levelUp(player));
        }
    }

    /**
     * Moves a {@link Player} to a {@link Local location} on the map
     *
     * @param player      {@link Player} to be moved
     * @param destination {@link Local location} of destination
     */
    public void playerNavigateTo(Player player, Local destination) {
        if (player.navigateTo(destination)) {
            player.addXp(this.gameSettings.getPlayerNavigatePoints());
            player.setLevel(levelUp(player));
        }
    }

    /**
     * Increments a {@link Player} level based on a specific formula
     *
     * @param player {@link Player} to level up
     */
    private int levelUp(Player player) { // TODO: fix formula
        System.err.println(Math.pow((player.getLevel() / player.getXp()), gameSettings.getPointsY()));
        int level = (int) Math.pow((player.getLevel() / player.getXp()), gameSettings.getPointsY());
        if (level == 0) {
            return 1;
        }

        return level;
    }
}
