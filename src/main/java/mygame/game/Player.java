package mygame.game;

import mygame.interfaces.IConnector;
import mygame.interfaces.ILocal;
import mygame.interfaces.IPlayer;
import mygame.interfaces.IPortal;
import mygame.structures.lists.UnorderedArray;

public class Player implements IPlayer {
    private static int nextId = 1;
    private final int id;
    private int energy;
    private Team team;
    // cooldowns (?)


    public Player(int energy, Team team) {
        this.id = nextId++;
        this.energy = energy;
        this.team = team;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public void conquerPortal(IPortal portal) {

    }

    @Override
    public void rechargeEnergy(IConnector connector) {

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
    public UnorderedArray<IPortal> getConqueredPortals() {
        return null;
    }

    @Override
    public ILocal getLocation() {
        return null;
    }

    @Override
    public void navigateTo(ILocal destination) {

    }

    @Override
    public void chargePortal(IPortal portal, int energy) {

    }
}
