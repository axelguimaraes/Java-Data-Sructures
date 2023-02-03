package mygame.game;

import java.util.Comparator;

public abstract class Local{
    private static int nextId;
    private final int id;
    private int energy;
    private Coordinates coordinates;
    private LocalType localType;

    public Local(int energy, Coordinates coordinates) {
        this.id = ++nextId;
        this.energy = energy;
        this.coordinates = coordinates;
    }
    public LocalType getLocalType() {
        return this.localType;
    }

    public void setLocalType(LocalType type) {
        this.localType = type;
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

    public int compareByID(Local other) {
        return Integer.compare(this.id, other.getId());
    }

    public int compareByEnergy(Local other) {
        return Integer.compare(this.energy, other.getEnergy());
    }

    public int compareByLocalType(Local other) {
        return this.localType.compareTo(other.getLocalType());
    }
}
