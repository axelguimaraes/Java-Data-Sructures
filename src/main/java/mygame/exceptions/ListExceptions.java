package mygame.exceptions;

/**
 * Class that represents the exceptions for lists
 */
public class ListExceptions extends Exception {

    public static final String ELEMENT_NOT_FOUND = "Elemento não existe na lista.";
    public static final String EMPTY_LIST = "Elemento não existe na lista.";
    public static final String OBJECT_NOT_COMPARABLE = "Objecto não é instancia de Comparable.";

    public ListExceptions() {
    }

    /**
     * Constructor to send a exception message
     * @param message the message to be sent
     */
    public ListExceptions(String message) {
        super(message);
    }
}
