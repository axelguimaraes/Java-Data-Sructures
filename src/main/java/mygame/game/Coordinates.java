package mygame.game;

/**
 * Class that represents a geographic location as {@link Coordinates}
 */
public class Coordinates {
    private final double latitude;
    private final double longitude;

    /**
     * Constructor for the {@link Coordinates} class
     * @param latitude represents the geographic position X on the Equador axis
     * @param longitude represents the geographic position Y on the Greenwich axis
     */
    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Getter for the latitude
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Getter for the longitude
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Lists all the information about the {@link Coordinates}
     * @return {@link String} with all the information
     */
    public String toString() {
        return this.latitude + ", " + this.longitude;
    }
}
