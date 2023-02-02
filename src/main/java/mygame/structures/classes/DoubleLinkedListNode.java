package mygame.structures.classes;

public class DoubleLinkedListNode<T> {

    private T data;
    private DoubleLinkedListNode<T> prev;
    private DoubleLinkedListNode<T> next;

    /**
     * Default DllNode class constructor
     */
    public DoubleLinkedListNode() {
        this.next = null;
        this.prev = null;
    }

    /**
     * DllNode class constructor
     *
     * @param data
     */
    public DoubleLinkedListNode(T data) {
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
    public DoubleLinkedListNode<T> getPrev() {
        return this.prev;
    }

    /**
     * Setter for the previous Node.
     *
     * @param prev the previous Node
     */
    public void setPrev(DoubleLinkedListNode<T> prev) {
        this.prev = prev;
    }

    /**
     * Getter for the next Node.
     *
     * @return the next Node
     */
    public DoubleLinkedListNode<T> getNext() {
        return this.next;
    }

    /**
     * Setter for the next Node.
     *
     * @param next the next Node
     */
    public void setNext(DoubleLinkedListNode<T> next) {
        this.next = next;
    }
}
