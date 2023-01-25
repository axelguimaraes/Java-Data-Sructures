package mygame.exceptions;

/**
 * Class that represents the exceptions for map
 */
public class MapException extends Exception{

    public static final String LOCATION_NOT_LOADED = "Location not loaded in map.";

    /**
     * Constructor to send a exception message
     * @param message the message to be sent
     */
    public MapException(String message) {
        super(message);
    }
}
