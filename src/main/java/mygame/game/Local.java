package mygame.game;

public abstract class Local{
    private static int nextId;
    private final int id;
    private int energy;
    private Coordinates coordinates;

    public Local(int energy, Coordinates coordinates) {
        this.id = ++nextId;
        this.energy = energy;
        this.coordinates = coordinates;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getId() {
        return this.id;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
