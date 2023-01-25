package mygame.interfaces;

import mygame.game.Coordinates;

public interface ILocal {
    public int getEnergy();
    public void setEnergy(int energy);
    public String getId();
    public Coordinates getCoordinates();
    public void setCoordinates(Coordinates coordinates);
}
