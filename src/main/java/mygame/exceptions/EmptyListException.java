package mygame.exceptions;

/**
 * Class that represents the exceptions for a empty list
 */
public class EmptyListException extends Exception {

    /**
     * Constructor to send a exception message
     * @param message the message to be sent
     */
    public EmptyListException(String message) {
        super(message);
    }
}
