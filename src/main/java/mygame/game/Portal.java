package mygame.game;

import mygame.interfaces.IPortal;

public class Portal extends Local implements IPortal {
    private Team team;
    private Player conqueror;

    public Portal(int energy, Coordinates coordinates, Team team, Player conqueror) {
        super(energy, coordinates);
        this.team = team;
        this.conqueror = conqueror;
    }

    @Override
    public Team getTeam() {
        return this.team;
    }

    @Override
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public Player getConqueror() {
        return this.conqueror;
    }

    @Override
    public void setConqueror(Player player) {
        this.conqueror = player;
    }

    @Override
    public void rechargeEnergy(int energy) {

    }
}
