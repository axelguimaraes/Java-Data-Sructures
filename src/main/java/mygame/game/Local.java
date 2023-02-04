package mygame.game;

import java.util.Comparator;

/**
 * Abstract class that represents a {@link Local}
 */
public abstract class Local {
    private static int nextId;
    private final int id;
    private int energy;
    private Coordinates coordinates;
    private LocalType localType;

    /**
     * Constructor for the {@link Local}. It generates an auto-incremented ID that identifies the {@link Local}
     *
     * @param energy      ammount of energy that the {@link Local} contains
     * @param coordinates geographic location of the {@link Local}, using {@link Coordinates}
     */
    public Local(int energy, Coordinates coordinates) {
        this.id = ++nextId;
        this.energy = energy;
        this.coordinates = coordinates;
    }

    /**
     * Getter for the {@link LocalType}
     * @return Location {@link LocalType}
     */
    public LocalType getLocalType() {
        return this.localType;
    }

    /**
     * Setter for the {@link LocalType}
     * @param type {@link LocalType} of {@link Local}
     */
    public void setLocalType(LocalType type) {
        this.localType = type;
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
     * Getter for the ID
     * @return ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter for the {@link Coordinates}
     * @return {@link Coordinates}
     */
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * Setter for the {@link Coordinates}
     * @param coordinates {@link Coordinates}
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Compares two {@link Local locations} by their ID
     * @param other {@link Local} to be compared to
     * @return '-1' if lesser; '0' if equal; '1' if greater
     */
    public int compareByID(Local other) {
        return Integer.compare(this.id, other.getId());
    }

    /**
     * Compares two {@link Local locations} by their energy
     * @param other {@link Local} to be compared to
     * @return '-1' if lesser; '0' if equal; '1' if greater
     */
    public int compareByEnergy(Local other) {
        return Integer.compare(this.energy, other.getEnergy());
    }

    /**
     * Compares two {@link Local locations} by their {@link LocalType}
     * @param other {@link Local} to be compared to
     * @return '-1' if lesser; '0' if equal; '1' if greater
     */
    public int compareByLocalType(Local other) {
        return this.localType.compareTo(other.getLocalType());
    }
}
