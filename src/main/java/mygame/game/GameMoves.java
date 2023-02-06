package mygame.game;

public class GameMoves {
     private final GameSettings gameSettings;

    public GameMoves(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    public void playerConquerPortal(Player player, Portal portal) {
        if (player.conquerPortal(portal)) {
            player.addXp(this.gameSettings.getPortalConquestPoints());
        }
    }

    public void playerChargePortal(Player player, int energy) {
        if (player.chargePortal(energy)) {
            player.addXp(this.gameSettings.getPortalChargingPoints());
        }
    }

    public void playerRechargeInConnector(Player player, Connector connector) {
        if (player.rechargeEnergy(connector)) {
            player.addXp(this.gameSettings.getConnectorPlayerChargingPoints());
        }
    }

    public void playerNavigateTo(Player player, Local destination) {
        if (player.navigateTo(destination)) {
            player.addXp(this.gameSettings.getPlayerNavigatePoints());
        }
    }

    private void levelUp(Player player) {
        
    }
}
