package mygame.game;

/**
 * Class that represents {@link GameSettings}, which defines global game settings
 */
public class GameSettings {
    private double portalConquestPoints;
    private double portalChargingPoints;
    private double connectorPlayerChargingPoints;
    private double playerNavigatePoints;
    private double pointsY;

    /**
     * Constructor for the {@link GameSettings} class
     * @param portalConquestPoints experience points to earn by conquering a {@link Portal}
     * @param portalChargingPoints experience points to charge by conquering a {@link Portal}
     * @param connectorPlayerChargingPoints experience points to earn by charging a {@link Player} in a {@link Connector}
     * @param playerNavigatePoints experience points to earn by moving a {@link Player} to another {@link Local location} of the {@link GameMap map}
     */
    public GameSettings(double portalConquestPoints, double portalChargingPoints, double connectorPlayerChargingPoints, double playerNavigatePoints, double pointsY) {
        this.portalConquestPoints = portalConquestPoints;
        this.portalChargingPoints = portalChargingPoints;
        this.connectorPlayerChargingPoints = connectorPlayerChargingPoints;
        this.playerNavigatePoints = playerNavigatePoints;
        this.pointsY = pointsY;
    }

    /**
     * Getter for portal conquest points
     * @return experience points
     */
    public double getPortalConquestPoints() {
        return portalConquestPoints;
    }

    /**
     * Setter for portal conquest points
     * @param portalConquestPoints experience points
     */
    public void setPortalConquestPoints(double portalConquestPoints) {
        this.portalConquestPoints = portalConquestPoints;
    }

    /**
     * Getter for portal charging points
     * @return experience points
     */
    public double getPortalChargingPoints() {
        return portalChargingPoints;
    }

    /**
     * Setter for portal charging points
     * @param portalChargingPoints experience points
     */
    public void setPortalChargingPoints(double portalChargingPoints) {
        this.portalChargingPoints = portalChargingPoints;
    }

    /**
     * Getter for player charging in connector points
     * @return experience points
     */
    public double getConnectorPlayerChargingPoints() {
        return connectorPlayerChargingPoints;
    }

    /**
     * Setter for player charging in connector points
     * @param connectorPlayerChargingPoints experience points
     */
    public void setConnectorPlayerChargingPoints(double connectorPlayerChargingPoints) {
        this.connectorPlayerChargingPoints = connectorPlayerChargingPoints;
    }

    /**
     * Getter for player navigation points
     * @return experience points
     */
    public double getPlayerNavigatePoints() {
        return playerNavigatePoints;
    }

    /**
     * Setter for player navigation points
     * @param playerNavigatePoints experience points
     */
    public void setPlayerNavigatePoints(double playerNavigatePoints) {
        this.playerNavigatePoints = playerNavigatePoints;
    }


    /**
     * Getter for Y value of points formula
     * @return Y value
     */
    public double getPointsY() {
        return pointsY;
    }

    /**
     * Setter for Y value of points formula
     * @param pointsY Y value
     */
    public void setPointsY(double pointsY) {
        this.pointsY = pointsY;
    }

    /**
     * String representation of the {@link GameSettings} class
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "GameSettings{" +
                "portalConquestPoints=" + portalConquestPoints +
                ", portalChargingPoints=" + portalChargingPoints +
                ", connectorPlayerChargingPoints=" + connectorPlayerChargingPoints +
                ", playerNavigatePoints=" + playerNavigatePoints +
                ", pointsY=" + pointsY +
                '}';
    }
}
