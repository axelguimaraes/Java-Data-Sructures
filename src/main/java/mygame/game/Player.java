package mygame.game;

import mygame.exceptions.ListExceptions;
import mygame.exceptions.PlayerWithNoTeamException;
import mygame.structures.classes.ArrayUnorderedList;

import java.text.DecimalFormat;
import java.util.Iterator;

/**
 * Class that represents a {@link Player}
 */
public class Player {
    private static int nextId;
    private final int id;

    private int energy;

    private int maxEnergy;
    private Team team;
    private String name;
    private int currentPositionID;
    private int level;
    private double xp;
    private GameMap map;
    private ArrayUnorderedList<Integer> conqueredPortalsIDs;

    public Player(String name, Team team, int level,double xp, int maxEnergy, int energy) throws PlayerWithNoTeamException {
        this.id = ++nextId;
        this.name = name;
        this.energy = 0;
        this.level = level;
        this.team = team;
        this.maxEnergy = maxEnergy;
        this.energy = energy;
        this.currentPositionID = -1;
        this.level = 1;
        this.xp = 0;
        this.conqueredPortalsIDs = new ArrayUnorderedList<>();

        if (this.team == null || this.team.equals(Team.NONE)) {
            throw new PlayerWithNoTeamException(PlayerWithNoTeamException.PLAYER_NO_TEAM);
        }
    }

    /**
     * Constructor for the {@link Player}. It generates an auto-incremented ID that identifies the {@link Player}. It
     * also sets its current position as '-1', meaning that it has no associated position at the moment. It also initiates
     * its level as 1 and experience points (xp) as 0. Lastly it creates a {@link ArrayUnorderedList list} of conquered
     * portals, which will contain each conquered portal by the {@link Player}
     * @param name {@link Player Player's} name
     * @param team {@link Team} to associate the {@link Player}
     * @throws PlayerWithNoTeamException thrown when the {@link Player} is created with no associated {@link Team}
     */
    public Player(String name, Team team) throws PlayerWithNoTeamException {
        this.name = name;
        this.id = ++nextId;
        this.energy = 0;
        this.team = team;
        this.currentPositionID = -1;
        this.level = 1;
        this.xp = 0;
        this.conqueredPortalsIDs = new ArrayUnorderedList<>();

        if (this.team == null || this.team.equals(Team.NONE)) {
            throw new PlayerWithNoTeamException(PlayerWithNoTeamException.PLAYER_NO_TEAM);
        }
    }

    /**
     * Adds a {@link Portal} to the conquered portals list
     * @param portalID ID of the conquered {@link Portal} to add
     */
    public void addToConqueredPortalsList(int portalID) {
        this.conqueredPortalsIDs.addToRear(portalID);
    }

    /**
     * Removes a {@link Portal} from the conquered portals list
     * @param portalID ID of the {@link Portal} to remove
     */
    public void removeFromConqueredPortalsList(int portalID) {
        this.conqueredPortalsIDs.remove(portalID);
    }

    /**
     * Getter for the {@link ArrayUnorderedList list} of all the conquered {@link Portal portals} by the {@link Player}
     * @return {@link ArrayUnorderedList list} of conquered portals
     */
    public ArrayUnorderedList<Integer> getConqueredPortalsIDs() {
        return this.conqueredPortalsIDs;
    }

    /**
     * Setter for the {@link GameMap}
     * @param map {@link GameMap}
     */
    public void setMap(GameMap map) {
        this.map = map;
    }

    /**
     * Getter for the ID
     * @return ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter for the level
     * @return level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Setter for the level
     * @param level level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Increases the {@link Player player's} level by 1
     */
    public void levelUp() {
        this.level++;
    }

    /**
     * Getter for the experience points
     * @return experience points
     */
    public double getXp() {
        return xp;
    }

    /**
     * Setter for the experience points
     * @param xp experience points
     */
    public void setXp(double xp) {
        this.xp = xp;
    }

    /**
     * Adds an ammount of experience points to the already existing ammount
     * @param xp experience points to add
     */
    public void addXp(double xp) {
        this.xp += xp;
    }

    /**
     * Getter for the energy
     * @return energy
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     * Setter for the energy
     * @param energy energy
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Adds an ammount of energy to the already existing ammount
     * @param energy
     */
    public void addEnergy(int energy) {
        this.energy += energy;
    }

    /**
     * Conquers a {@link Portal} by the {@link Player}
     * @param portal {@link Portal} to be conquered
     * @return true if conquered; false if not conquered
     */
    public boolean conquerPortal(Portal portal) { // TESTED
        return portal.getConquered(this);
    }

    /**
     * Recharges a {@link Player}'s energy with the {@link Connector}'s ammount
     * @param connector {@link Connector} to charge {@link Player}
     * @return true if recharged; false if not recharged
     */
    public boolean rechargeEnergy(Connector connector) { // TESTED
        return connector.chargePlayer(this);
    }

    /**
     * Getter for the {@link Team}
     * @return {@link Team}
     */
    public Team getTeam() {
        return this.team;
    }

    /**
     * Setter for the {@link Team}
     * @param team {@link Team}
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Getter for the conquuered portals {@link ArrayUnorderedList list}
     * @return {@link ArrayUnorderedList list} of conquered portals
     */
    public ArrayUnorderedList<Portal> getConqueredPortals() {
        return null;
    }

    /**
     * Getter for the {@link Local location}
     * @return {@link Local}
     */
    public Local getLocation() {
        return null;
    }

    /**
     * Navigates to an existing {@link Local location} on the {@link GameMap} using the shortest path
     * @param destination {@link Local location} of destination
     */
    public void navigateTo(Local destination) {
        boolean found = false;
        Iterator<Local> it = this.map.getMap().iteratorBFS(0);
        while (it.hasNext()) {
            Local local = it.next();
            if (local.equals(destination)) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.err.println("Location not found!");
            return;
        }

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
        s.append("\tDistance: ").append(df.format(this.map.getShortestPathWeight(this.currentPositionID, destination.getId()))).append("km");

        System.out.println(s);

        this.currentPositionID = destination.getId();
    }

    /**
     * Getter for the {@link Player}'s current position
     * @return ID of the current {@link Local position}
     */
    public int getCurrentPositionID() {
        return currentPositionID;
    }

    /**
     * Setter for the {@link Player}'s current position
     * @param currentPositionID ID for the current {@link Local} position
     */
    public void setCurrentPositionID(int currentPositionID) {
        this.currentPositionID = currentPositionID;
    }

    /**
     * Gets current {@link Local position} information
     * @return {@link String} containing all current {@link Local position} information
     */
    public String getCurrentPositionInfo() {
        return this.map.getLocalByID(this.currentPositionID).toString();
    }

    /**
     * Charges the {@link Portal} where the {@link Player} is located at, using a given ammount of its energy
     * @param energy ammount of energy to charge the {@link Portal}
     * @return true if charged; false if not charged
     */
    public boolean chargePortal(int energy) {
        if (!((this.map.getLocalByID(this.currentPositionID))instanceof Portal)) {
            System.err.println("This player isn't on any portal at the moment");
            return false;
        }

        if (this.energy < energy) {
            System.err.println("This player doesn't have the required ammount energy");
            return false;
        }

        Portal portal = (Portal) this.map.getLocalByID(this.currentPositionID);
        return portal.rechargeEnergy(this, energy);
    }

    /**
     * Getter for the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Check if a {@link Portal} belongs to the player's {@link Team}
     * @param portal {@link Portal} to be checked
     * @return true if it belongs; false if it doesn't belong
     */
    public boolean isFromDifferentTeam(Portal portal) {
        if (portal.getTeam().equals(Team.NONE)) {
            return false;
        }

        return !this.team.equals(portal.getTeam());
    }

    /**
     * Lists all the information regarding the {@link Player}
     * @return {@link String} with all the information
     */
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

    public String showPlayerData(){
        return "==============\n" +
                "ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Energy: " + energy + "\n" +
                "Level: " + level + "\n" +
                "Team: " + team + "\n" +
                "Max Energy: " + maxEnergy + "\n" +
                "Current Position ID: " + currentPositionID + "\n" +
                "XP: " + xp + "\n" +
                "Conquered Portals IDs: " + conqueredPortalsIDs + "\n" +
                "==============\n";
    }

    /**
     * Check if an {@link Object} is equal to the {@link Player}
     * @param obj {@link Object} to be compared to
     * @return true if it's equal; false if it's not equal
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }

        Player player = (Player) obj;
        return player.getName().equals(this.name);
    }

    /**
     * Compares two {@link Player players} by their name
     * @param other {@link Player} to be compared to
     * @return '-1' if lesser; '0' if equal; '1' if greater
     */
    public int compareByName(Player other) {
        return this.name.compareTo(other.getName());
    }

    /**
     * Compares two {@link Player players} by their energy
     * @param other {@link Player} to be compared to
     * @return '-1' if lesser; '0' if equal; '1' if greater
     */
    public int compareByEnergy(Player other) {
        return Integer.compare(this.energy, other.getEnergy());
    }

    /**
     * Compares two {@link Player players} by their {@link Team}
     * @param other {@link Player} to be compared to
     * @return '-1' if lesser; '0' if equal; '1' if greater
     */
    public int compareByTeam(Player other) {
        return this.team.compareTo(other.getTeam());
    }

    /**
     * Compares two {@link Player players} by their level
     * @param other {@link Player} to be compared to
     * @return '-1' if lesser; '0' if equal; '1' if greater
     */
    public int compareByLevel(Player other) {
        return Integer.compare(this.level, other.getLevel());
    }

    /**
     * Compares two {@link Player players} by their numbered of conquered {@link Portal portals}
     * @param other {@link Player} to be compared to
     * @return '-1' if lesser; '0' if equal; '1' if greater
     */
    public int compareByNumberOfConqueredPortals(Player other) {
        return Integer.compare(this.conqueredPortalsIDs.size(), other.getConqueredPortalsIDs().size());
    }
}
