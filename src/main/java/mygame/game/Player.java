package mygame.game;

import mygame.exceptions.ListExceptions;
import mygame.exceptions.PlayerWithNoTeamException;
import mygame.structures.classes.ArrayUnorderedList;

import java.text.DecimalFormat;

public class Player {
    private static int nextId;
    private final int id;
    private int energy;
    private Team team;
    private String name;
    private int currentPositionID;
    private int level;
    private double xp;
    private GameMap map;

    public Player(String name, Team team) throws PlayerWithNoTeamException {
        this.name = name;
        this.id = ++nextId;
        this.energy = 0;
        this.team = team;
        this.currentPositionID = -1;
        this.level = 1;
        this.xp = 0;

        if (this.team == null || this.team.equals(Team.NONE)) {
            throw new PlayerWithNoTeamException(PlayerWithNoTeamException.PLAYER_NO_TEAM);
        }
    }

    public void setMap(GameMap map) {
        this.map = map;
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

    public ArrayUnorderedList<Portal> getConqueredPortals() {
        return null;
    }

    public Local getLocation() {
        return null;
    }

    public void navigateTo(Local destination) {
        DecimalFormat df = new DecimalFormat("0.00");

        StringBuilder s = new StringBuilder("Navigated to ");
        if (destination instanceof Portal) {
            s.append(((Portal) destination).getName());
        } else {
            s.append("Connector ID: ").append(destination.getId());
        }
        s.append("\tPath: ");
        for (Local local : this.map.getShortestPathToLocal(this.currentPositionID, destination.getId())) {
            s.append(local.getId()).append(" ");
        }
        s.append("\tDistance: ").append(df.format(this.map.getShortestPathweight(this.map.getLocalByID(this.currentPositionID), this.map.getLocalByID(destination.getId())))).append("km");

        System.out.println(s);

        this.currentPositionID = destination.getId();
    }

    public int getCurrentPositionID() {
        return currentPositionID;
    }

    public void setCurrentPositionID(int currentPositionID) {
        this.currentPositionID = currentPositionID;
    }

    public String getCurrentPositionInfo() {
        return this.map.getLocalByID(this.currentPositionID).toString();
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
        String name;
        if (this.map.getLocalByID(this.currentPositionID) instanceof Portal) {
            name = ((Portal) this.map.getLocalByID(this.currentPositionID)).getName();
        } else {
            name = "Connector";
        }
        return "PLAYER\n" +
                "Name: " + this.name + "\n" +
                "Energy: " + this.energy + "\n" +
                "Team: " + this.team.toString() + "\n" +
                "Level: " + this.level + "\n" +
                "Current position: " + name + " ID: " + this.currentPositionID;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }

        Player player = (Player) obj;
        return player.getName().equals(this.name);
    }

    public int compareByName(Player other) {
        return this.name.compareTo(other.getName());
    }

    public int compareByEnergy(Player other) {
        return Integer.compare(this.energy, other.getEnergy());
    }

    public int compareByTeam(Player other) {
        return this.team.compareTo(other.getTeam());
    }

    public int compareByLevel(Player other) {
        return Integer.compare(this.level, other.getLevel());
    }
}
