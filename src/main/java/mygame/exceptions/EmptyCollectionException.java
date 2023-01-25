package mygame.exceptions;

/**
 * Class that represents the exceptions for an empty collection
 */
public class EmptyCollectionException extends Exception {

    public static final String EMPTY_COLLECTION = "Empty collection.";

    /**
     * Personalized string message for this exception
     * @param message message to be passed to the user
     */
    public EmptyCollectionException(String message) {
        super(message);
    }
}
