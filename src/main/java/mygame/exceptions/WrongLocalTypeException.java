package mygame.exceptions;

/**
 * Class that represents the exceptions for wrong local types
 */
public class WrongLocalTypeException extends Exception{

    public static final String WRONG_TYPE = "Type doesn't match local.";
    public static final String WRONG_METHOD = "Method doesn't match local type.";

    public WrongLocalTypeException(){};

    /**
     * Constructor to send a exception message
     * @param message the message to be sent
     */
    public WrongLocalTypeException(String message) {
        super(message);
    }
}
