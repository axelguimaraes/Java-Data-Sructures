package mygame.exceptions;

/**
 * Class that represents the exceptions for a element not found
 */
public class ElementNotFoundException extends Exception {

    /**
     * Constructor to send a exception message
     * @param message the message to be sent
     */
    public ElementNotFoundException(String message) {
        super(message);
    }
}
