package mygame.game;

public class Portal extends Local {
    private String state;
    private String player;

    public Portal(int id, double longitude, double latitude, double energy, String state, String player) {
        super(id, longitude, latitude, energy);
        this.state = state;
        this.player = player;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public boolean canConquer(Player player) {
        if (super.getOwnership().equals("neutral")) {
            return true;
        } else if (super.getOwnership().equals(player.getTeam())) {
            return true;
        } else {
            System.out.println("Cannot conquer. Portal already belongs to another team.");
            return false;
        }
    }

    public boolean attack(Player player) {
        if (!super.getOwnership().equals(player.getTeam())) {
            int playerEnergy = player.getCurrentEnergy();
            if (playerEnergy >= super.getEnergy()) {
                player.setCurrentEnergy(playerEnergy - player.getCurrentEnergy());
                super.setEnergy(0);
                //super.setOwnership("neutral");
                System.out.println("Portal conquered!");
                return true;
            } else {
                super.setEnergy(super.getEnergy() - playerEnergy);
                player.setCurrentEnergy(0);
                System.out.println("Not enough energy to conquer the portal. Portal energy remaining: " + player.getCurrentEnergy());
                return false;
            }
        } else {
            System.out.println("Cannot attack own team's portal.");
            return false;
        }
    }

    public boolean recharge(Player player, int energy) {
        if (super.getOwnership().equals(player.getTeam())) {
            int playerEnergy = player.getCurrentEnergy();
            if (playerEnergy >= energy) {
                player.setCurrentEnergy(playerEnergy - energy);
                super.setEnergy(super.getEnergy() + energy);
                System.out.println("Portal recharged successfully!");
                return true;
            } else {
                System.out.println("Not enough energy to recharge portal.");
                return false;
            }
        } else {
            System.out.println("Cannot recharge another team's portal.");
            return false;
        }
    }
}