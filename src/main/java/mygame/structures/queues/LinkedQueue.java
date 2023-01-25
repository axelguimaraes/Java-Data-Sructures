package mygame.structures.queues;


import mygame.exceptions.EmptyCollectionException;
import mygame.structures.lists.LinearNode;

/**
 * 
 * @author 
 * @param <T> 
 */
public class LinkedQueue<T> implements QueueADT<T> {

    private LinearNode<T> front;
    private LinearNode<T> rear;
    private int size;

    /**
     * Constructor
     */
    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Adds one element to the rear of this queue.
     *
     * @param element the element to be added to
     *                the rear of this queue
     */
    @Override
    public void enqueue(T element) {
        LinearNode<T> newElement = new LinearNode<>(element);

        /**
         * Se o front for nulo, ou seja, não haver elementos na queue
         * Adiciona-se à primeira posicao
         *
         * Caso já haja posições preenchidas, o novo elemento ocupa a posição
         * traseira (rear) que esteja disponivel e é incrementado o tamanho
         */
        if (this.front == null) {
            this.front = newElement;
        } else {
            this.rear.setNext(newElement);
        }

        this.rear = newElement;
        this.size++;
    }

    /**
     * Removes and returns the element at the front of
     * this queue.
     *
     * @return the element at the front of this queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue empty");
        }

        T element = this.front.getElement();
        this.front = this.front.getNext();
        this.size--;

        return element;
    }

    /**
     * Returns without removing the element at the front of
     * this queue.
     *
     * @return the first element in this queue
     * @throws EmptyCollectionException if the queue is empty
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue empty");
        }

        return this.front.getElement();
    }

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue is empty
     */
    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the integer representation of the size
     * of this queue
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns a string representation of this queue
     *
     * @return the string representation of thsi queue
     */
    @Override
    public String toString() {
        String str = "";
        LinearNode<T> node = this.front;

        for (int i = 0; i < this.size; i++) {
            str = str + node.getElement() + " ";
            node = node.getNext();
        }

        return str + "";
    }
}
