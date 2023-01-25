package mygame.structures.queues;


import mygame.exceptions.EmptyCollectionException;

/**
 * 
 * @author
 * @param <T> 
 */
public class CircularArrayQueue<T> implements QueueADT<T> {

    private final int DEFAULT_CAPACITY = 5;
    private T[] queue;
    private int front;
    private int rear;
    private int size;

    /**
     * Default constructor
     */
    public CircularArrayQueue() {
        this.front = 0;
        this.rear = 0;
        this.size = 0;
        this.queue = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Constructor with param
     * @param tamanho
     */
    public CircularArrayQueue(int tamanho) {
        this.front = 0;
        this.rear = 0;
        this.size = 0;
        this.queue = (T[]) (new Object[tamanho]);
    }

    /**
     * If queue is full, this method extends the array capacity
     */
    private void expandCapacity() {
        T[] expand = (T[]) (new Object[this.queue.length + DEFAULT_CAPACITY]);

        for (int i = 0; i < this.size; i++) {
            expand[i] = this.queue[this.front];
            this.front = (this.front + 1) % this.queue.length;
        }

        this.front = 0;
        this.rear = this.size;
        this.queue = expand;
    }

    /**
     * Adds one element to the rear of this queue
     * @param element the element to be added to the rear of this queue
     */
    @Override
    public void enqueue(T element) {
        if (size() == this.queue.length) {
            expandCapacity();
        }

        this.queue[this.rear] = element;
        this.rear = (this.rear + 1) % this.queue.length;
        this.size++;
    }

    /**
     * Removes and returns the element at the front of this queue.
     * @return the element at the front of this queue
     * @throws EmptyCollectionException if the queue is empty and has nothing to remove
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue empty");
        }

        T result = this.queue[this.front];
        this.queue[this.front] = null;
        this.front = (this.front + 1) % this.queue.length;
        this.size--;
        return result;
    }

    /**
     * Returns without removing the element at the front of this queue
     * @return the first element of this queue
     * @throws EmptyCollectionException if the queue is empty and has nothing to return
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Queue empty");
        }

        return this.queue[this.front];
    }

    /**
     * Returns true if this queue contains no elements
     * @return true if this queue is empty
     */
    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * Returns the number of elements in this queue
     * @return the integer representation of the size of this queue
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns a string representation of this queue.
     * @return the string representation of this queue
     */
    @Override
    public String toString() {
        String str = "";
        int tempFront = this.front;

        for (int i = 0; i < this.size; i++) {
            str += this.queue[tempFront].toString() + " ";
            tempFront = (tempFront + 1) % this.queue.length;
        }

        return str + "";
    }
}
