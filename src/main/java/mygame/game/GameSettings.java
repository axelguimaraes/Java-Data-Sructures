package mygame.game;

public class GameSettings {
    private int portalConquestPoints;
    private int portalChargingPoints;
    private int connectorPlayerChargingPoints;
    private int playerNavigatePoints;

    public GameSettings(int portalConquestPoints, int portalChargingPoints, int connectorPlayerChargingPoints, int playerNavigatePoints) {
        this.portalConquestPoints = portalConquestPoints;
        this.portalChargingPoints = portalChargingPoints;
        this.connectorPlayerChargingPoints = connectorPlayerChargingPoints;
        this.playerNavigatePoints = playerNavigatePoints;
    }

    public int getPortalConquestPoints() {
        return portalConquestPoints;
    }

    public void setPortalConquestPoints(int portalConquestPoints) {
        this.portalConquestPoints = portalConquestPoints;
    }

    public int getPortalChargingPoints() {
        return portalChargingPoints;
    }

    public void setPortalChargingPoints(int portalChargingPoints) {
        this.portalChargingPoints = portalChargingPoints;
    }

    public int getConnectorPlayerChargingPoints() {
        return connectorPlayerChargingPoints;
    }

    public void setConnectorPlayerChargingPoints(int connectorPlayerChargingPoints) {
        this.connectorPlayerChargingPoints = connectorPlayerChargingPoints;
    }

    public int getPlayerNavigatePoints() {
        return playerNavigatePoints;
    }

    public void setPlayerNavigatePoints(int playerNavigatePoints) {
        this.playerNavigatePoints = playerNavigatePoints;
    }

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
