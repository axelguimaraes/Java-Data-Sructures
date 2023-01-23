package mygame.game;

public class GameSettings {
    private int energy;
    private int maxEnergy;
    private Player ownership;

    public GameSettings(int energy, int maxEnergy, Player ownership) {
        this.energy = energy;
        this.maxEnergy = maxEnergy;
        this.ownership = ownership;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public Player getOwnership() {
        return ownership;
    }

    public void setOwnership(Player ownership) {
        this.ownership = ownership;
    }
}
