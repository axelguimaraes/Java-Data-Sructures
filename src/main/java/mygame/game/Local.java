package mygame.game;

import mygame.interfaces.ILocal;

public abstract class Local implements ILocal {
    private static int nextId;
    private final int id;
    private int energy;
    private Coordinates coordinates;

    public Local(int energy, Coordinates coordinates) {
        this.id = ++nextId;
        this.energy = energy;
        this.coordinates = coordinates;
    }

    @Override
    public int getEnergy() {
        return 0;
    }

    @Override
    public void setEnergy(int energy) {

    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
