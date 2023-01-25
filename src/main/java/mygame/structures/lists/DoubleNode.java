package mygame.structures.lists;

public class DoubleNode<T> {
    private T element;
    private DoubleNode<T> next, previous;

    /**
     * Constructor
     */
    public DoubleNode() {
    }

    /**
     * Constructor
     *
     * @param element element node
     */
    public DoubleNode(T element) {
        this.next = null;
        this.previous = null;
        this.element = element;
    }

    /**
     * Getter for the next node
     *
     * @return next node
     */
    public DoubleNode<T> getNext() {
        return this.next;
    }

    /**
     * Setter for the next node
     *
     * @param next next node
     */
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    /**
     * Getter for the previous node
     * @return previous node
     */
    public DoubleNode<T> getPrevious() {
        return previous;
    }

    /**
     * Setter
     * @param previous
     */
    public void setPrevious(DoubleNode<T> previous) {
        this.previous = previous;
    }

    /**
     * Getter for the element
     *
     * @return the element
     */
    public T getElement() {
        return this.element;
    }

    /**
     * Setter for the element
     *
     * @param element the element
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Method toString to print
     *
     * @return The element string
     */
    @Override
    public String toString() {
        return this.element.toString();
    }
}
