package mygame.structures.stacks;


import mygame.exceptions.EmptyCollectionException;
import mygame.structures.lists.LinearNode;

/**
 *
 * @author
 */
public class LinkedStack<T> implements StackADT<T> {
      
    private int size;
    private LinearNode<T> top;

    /**
     * Default constructor.
     */
    public LinkedStack() {
        this.size = 0;
        this.top = null;
    }

    /**
     * Adds a element to the top of the Stack.
     *
     * @param element element to be pushed onto stack
     */
    @Override
    public void push(T element) {
        LinearNode<T> newElement = new LinearNode<>(element);

        /**
         * Se o top for nulo, ou seja, não haver elementos na stack
         * O novo elemento fica nessa posição e é incrementado o tamanho da stack
         *
         * Caso já haja posições preenchidas, o novo elemento ocupa a posição
         * seguinte e é incrementado o tamanho da stack
         */
        if (this.size == 0) {
            this.top = newElement;
            this.size++;
        } else {
            newElement.setNext(this.top);
            this.top = newElement;
            this.size++;
        }
    }

    /**
     * Removes the element at the top of the stack and returns a reference to it.
     * 
     * Throws an EmptyCollectionException if the stack is empty
     *
     * @return T element removed from the top of the stack
     * @throws EmptyCollectionException if a pop is attempted on
     *                                  a empty stack
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Empty stack");
        } else {
            /**
             * É obtida a posicao do elemento a remover
             * O elemento (top) é substituido pelo seguinte
             * E de seguida o tamanho da stack é decrementado o size
             */
            T element = this.top.getElement();
            this.top = this.top.getNext();
            this.size--;

            return element;
        }
    }

    /**
     * Returns a reference to the element at the top of this stack.
     * The element is not removed from the stack.
     * Throws an EmptyCollectionException if the stack is empty.
     *
     * @return T element on the top of the stack.
     * @throws EmptyCollectionException if a peek is attempted on
     *                                  a empty stack
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack empty");
        } else {
            return this.top.getElement();
        }
    }

    /**
     * Method that returns true of the stack contains no elements
     *
     * @return a boolean value if the stack is empty or not
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method that returns the number of elements in the stack
     *
     * @return int number of elements in the stack
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Method that returns a string representation of the stack.
     *
     * @return String representation of the stack
     */
    @Override
    public String toString() {
        String str = "";
        LinearNode<T> node = this.top;

        for (int i = 0; i < this.size; i++) {
            str = str + node.getElement() + " ";
            node = node.getNext();
        }

        return str + "";
    }
}
