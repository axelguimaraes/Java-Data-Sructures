package mygame.structures.lists;

import mygame.exceptions.EmptyListException;

/**
 * 
 * @author 
 * @param <T> 
 */
public class LinkedListSentinel<T> {

    private int count;
    private LinearNode<T> head, tail;

    /**
     * Constructor for an empty list
     */
    public LinkedListSentinel() {
        this.head = new LinearNode<>(null);
        this.tail = new LinearNode<>(null);
    }

    /**
     * Getter for count
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Getter for node head
     *
     * @return the node head
     */
    public LinearNode<T> getHead() {
        return head;
    }

    /**
     * Getter for node tail
     *
     * @return the node tail
     */
    public LinearNode<T> getTail() {
        return tail;
    }

    private boolean isEmpty() {
        return (this.count == 0);
    }

    /**
     * Method to add to the list
     * @param element
     */
    public void add(T element) {
        // new node
        LinearNode<T> newNode = new LinearNode<>(element);

        newNode.setNext(this.head.getNext());
        this.head.setNext(newNode);
        this.count++;
    }

    /**
     * Methos that removes the first element equal to the pretended value
     *
     * @param element element to remove
     */
    public boolean remove(T element) {
        boolean found = false;

        LinearNode<T> previous = this.head;
        LinearNode<T> current = this.head.getNext();

        while (current.getElement() != null ) {
            if (current.getElement().equals(element)) {
                previous.setNext(current.getNext());
                this.count--;
                return true;
            } else {
                previous = current;
                current = current.getNext();
            }
        }

        return false;
    }

    /**
     * Imprimir todos os elementos da list
     */
    public void printList() throws EmptyListException {
        LinearNode node = this.head;
        //só imprime se houver elementos na lista
        while (node != null) {
            System.out.print(node.toString() + "\n");
            node = node.getNext();
        }
    }
}
