package mygame.structures.classes;

public class DoubleLinkedNode<T> {

    private T data;
    private DoubleLinkedNode<T> prev;
    private DoubleLinkedNode<T> next;

    /**
     * Default DllNode class constructor
     */
    public DoubleLinkedNode() {
        this.next = null;
        this.prev = null;
    }

    /**
     * DllNode class constructor
     *
     * @param data
     */
    public DoubleLinkedNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * Getter for the data
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Setter for the data
     *
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Getter for the previous Node.
     *
     * @return the previous Node.
     */
    public DoubleLinkedNode<T> getPrev() {
        return this.prev;
    }

    /**
     * Setter for the previous Node.
     *
     * @param prev the previous Node
     */
    public void setPrev(DoubleLinkedNode<T> prev) {
        this.prev = prev;
    }

    /**
     * Getter for the next Node.
     *
     * @return the next Node
     */
    public DoubleLinkedNode<T> getNext() {
        return this.next;
    }

    /**
     * Setter for the next Node.
     *
     * @param next the next Node
     */
    public void setNext(DoubleLinkedNode<T> next) {
        this.next = next;
    }
}
