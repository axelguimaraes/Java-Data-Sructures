package mygame.game;

/**
 * Class that represents {@link GameSettings}, which defines global game settings
 */
public class GameSettings {
    private int portalConquestPoints;
    private int portalChargingPoints;
    private int connectorPlayerChargingPoints;
    private int playerNavigatePoints;

    /**
     * Constructor for the {@link GameSettings} class
     * @param portalConquestPoints experience points to earn by conquering a {@link Portal}
     * @param portalChargingPoints experience points to charge by conquering a {@link Portal}
     * @param connectorPlayerChargingPoints experience points to earn by charging a {@link Player} in a {@link Connector}
     * @param playerNavigatePoints experience points to earn by moving a {@link Player} to another {@link Local location} of the {@link GameMap map}
     */
    public GameSettings(int portalConquestPoints, int portalChargingPoints, int connectorPlayerChargingPoints, int playerNavigatePoints) {
        this.portalConquestPoints = portalConquestPoints;
        this.portalChargingPoints = portalChargingPoints;
        this.connectorPlayerChargingPoints = connectorPlayerChargingPoints;
        this.playerNavigatePoints = playerNavigatePoints;
    }

    /**
     * Getter for portal conquest points
     * @return experience points
     */
    public int getPortalConquestPoints() {
        return portalConquestPoints;
    }

    /**
     * Setter for portal conquest points
     * @param portalConquestPoints experience points
     */
    public void setPortalConquestPoints(int portalConquestPoints) {
        this.portalConquestPoints = portalConquestPoints;
    }

    /**
     * Getter for portal charging points
     * @return experience points
     */
    public int getPortalChargingPoints() {
        return portalChargingPoints;
    }

    /**
     * Setter for portal charging points
     * @param portalChargingPoints experience points
     */
    public void setPortalChargingPoints(int portalChargingPoints) {
        this.portalChargingPoints = portalChargingPoints;
    }

    /**
     * Getter for player charging in connector points
     * @return experience points
     */
    public int getConnectorPlayerChargingPoints() {
        return connectorPlayerChargingPoints;
    }

    /**
     * Setter for player charging in connector points
     * @param connectorPlayerChargingPoints experience points
     */
    public void setConnectorPlayerChargingPoints(int connectorPlayerChargingPoints) {
        this.connectorPlayerChargingPoints = connectorPlayerChargingPoints;
    }

    /**
     * Getter for player navigation points
     * @return experience points
     */
    public int getPlayerNavigatePoints() {
        return playerNavigatePoints;
    }

    /**
     * Setter for player navigation points
     * @param playerNavigatePoints experience points
     */
    public void setPlayerNavigatePoints(int playerNavigatePoints) {
        this.playerNavigatePoints = playerNavigatePoints;
    }

    /**
     * String representation of the {@link GameSettings} class
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "GameSettings{" +
                "portalConquestPoints=" + portalConquestPoints +
                ", portalChargoingPoints=" + portalChargingPoints +
                ", connectorPlayerChargingPoints=" + connectorPlayerChargingPoints +
                ", playerNavigatePoints=" + playerNavigatePoints +
                '}';
    }
}
