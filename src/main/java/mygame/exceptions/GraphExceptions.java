package mygame.exceptions;

/**
 * Class that represents the exceptions for graphs
 */
public class GraphExceptions extends Exception {

    public final static String EMPTY_GRAPH = "Grafo está vazio.";
    public final static String ELEMENT_NOT_FOUND = "Elemento não encontrado.";
    public final static String PATH_NOT_FOUND = "Caminho não encontrado.";

    public GraphExceptions() {
    }

    /**
     * Constructor to send a exception message
     * @param message the message to be sent
     */
    public GraphExceptions(String message) {
        super(message);
    }
}
