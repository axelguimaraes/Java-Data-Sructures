package mygame.game;

import mygame.exceptions.EmptyCollectionException;
import mygame.exceptions.GraphExceptions;
import mygame.exceptions.ListExceptions;
import mygame.exceptions.PlayerWithNoTeamException;
import mygame.structures.lists.UnorderedArrayList;
import mygame.structures.stacks.LinkedStack;

public class Player {
    private static int nextId;
    private final int id;
    private int energy;
    private Team team;
    private String name;
    private Local currentPosition;
    private int level;
    private double xp;

    public Player(String name, Team team) throws PlayerWithNoTeamException {
        this.name = name;
        this.id = ++nextId;
        this.energy = 0;
        this.team = team;
        this.currentPosition = null;
        this.level = 1;
        this.xp = 0;

        if (this.team == null || this.team.equals(Team.NONE)) {
            throw new PlayerWithNoTeamException(PlayerWithNoTeamException.PLAYER_NO_TEAM);
        }
    }

    public int getId() {
        return this.id;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void levelUp() {
        this.level++;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public void addXp(double xp) {
        this.xp += xp;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void addEnergy(int energy) {
        this.energy += energy;
    }

    public boolean conquerPortal(Portal portal) { // TESTED
        return portal.getConquered(this);
    }

    public boolean rechargeEnergy(Connector connector) throws ListExceptions { // TESTED
        return connector.chargePlayer(this);
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public UnorderedArrayList<Portal> getConqueredPortals() {
        return null;
    }

    public Local getLocation() {
        return null;
    }

    public void navigateTo(Local destination) {
        this.currentPosition = destination; // TODO: here
    }

    public Local getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Local currentPosition) {
        this.currentPosition = currentPosition;
    }

    public boolean chargePortal(Portal portal, int energy) {
        return portal.rechargeEnergy(this, energy);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFromDifferentTeam(Portal portal) {
        if (portal.getTeam().equals(Team.NONE)) {
            return false;
        }

        return !this.team.equals(portal.getTeam());
    }

    public String toString() {
        return "PLAYER\n" +
                "Name: " + this.name + "\n" +
                "Energy: " + this.energy + "\n" +
                "Team: " + this.team.toString() + "\n" +
                "Level: " + this.level + "\n" +
                "Current position: " + this.currentPosition.getLocalType() + " ID: " + this.currentPosition.getId();
    }
}
