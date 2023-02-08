package mygame.game;

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
            playerPortalConquestXP(player);
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
            playerPortalChargingXP(player);
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
            playerConnectorRechargingXP(player);
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
            playerNavigateToXP(player);
        }
    }

    /**
     * Navigates to an existing location, whilst forcefully going through other locations
     * @param player {@link Player} to navigate
     * @param destination list of {@link Local destinations} to travel through
     */
    public void playerNavigateToByMultipleLocations(Player player, Local... destination) {
        if (player.navigateToByMultipleLocations(destination)) {
            playerNavigateToXP(player);
        }
    }

    /**
     * Sets player's XP and level based on the executed move
     *
     * @param player {@link Player} to make move
     */
    private void playerPortalConquestXP(Player player) {
        player.addXp(Math.pow(player.getLevel() / gameSettings.getPortalConquestPoints(), gameSettings.getPointsY()));
        player.setLevel((int) (gameSettings.getPortalConquestPoints() * Math.sqrt(player.getXp())));
    }

    /**
     * Sets player's XP and level based on the executed move
     *
     * @param player {@link Player} to make move
     */
    private void playerPortalChargingXP(Player player) {
        player.addXp(Math.pow(player.getLevel() / gameSettings.getPortalChargingPoints(), gameSettings.getPointsY()));
        player.setLevel((int) (gameSettings.getPortalChargingPoints() * Math.sqrt(player.getXp())));
    }

    /**
     * Sets player's XP and level based on the executed move
     *
     * @param player {@link Player} to make move
     */
    private void playerConnectorRechargingXP(Player player) {
        player.addXp(Math.pow(player.getLevel() / gameSettings.getConnectorPlayerChargingPoints(), gameSettings.getPointsY()));
        player.setLevel((int) (gameSettings.getConnectorPlayerChargingPoints() * Math.sqrt(player.getXp())));
    }

    /**
     * Sets player's XP and level based on the executed move
     *
     * @param player {@link Player} to make move
     */
    private void playerNavigateToXP(Player player) {
        player.addXp(Math.pow(player.getLevel() / gameSettings.getPlayerNavigatePoints(), gameSettings.getPointsY()));
        player.setLevel((int) (gameSettings.getPlayerNavigatePoints() * Math.sqrt(player.getXp())));
    }
}