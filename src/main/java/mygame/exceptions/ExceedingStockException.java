package mygame.exceptions;

/**
 * Class that represents the exceptions for exceeding stock
 */
public class ExceedingStockException extends Exception {

    public static final String EXCEEDINIG_STOCK = "Stock exceeds warehouse capacity.";

    /**
     * Constructor to send a exception message
     * @param message the message to be sent
     */
    public ExceedingStockException(String message) {
        super(message);
    }
}
