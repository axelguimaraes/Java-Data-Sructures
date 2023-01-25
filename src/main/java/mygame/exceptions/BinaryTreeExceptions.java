package mygame.exceptions;

/**
 * Class that represents the exceptions for binary tree
 */
public class BinaryTreeExceptions extends Exception {

    public static final String EMPTY_TREE = "Arvore vazia";
    public static final String ELEMENT_NOT_FOUND = "Elemento não encontrado.";

    public BinaryTreeExceptions() {
    }

    /**
     * Constructor to send a exception message
     * @param message the message to be sent
     */
    public BinaryTreeExceptions(String message) {
        super(message);
    }
}
  
