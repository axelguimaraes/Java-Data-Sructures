package mygame.structures.stacks;




import mygame.exceptions.EmptyCollectionException;

import java.util.Arrays;

/**
 *
 * @author samue
 */
public class ArrayStack<T> implements StackADT<T> {

    
    // constant to represent the default capacity of the array
    private final int DEFAULT_CAPACITY = 5;

    // int that represents both the number of elements and the next available position in the array
    private int top;

    // array of generic elements to represent the stack
    private T[] stack;
    
    /**
     * Creates an empty stack using the default capacity
     */
    public ArrayStack() {
        this.top = 0;
        this.stack = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Creates an empty stack using a given capacity
     * @param initialCapacity the capacity to be used
     */
    public ArrayStack(int initialCapacity) {
        this.top = 0;
        this.stack = (T[]) (new Object[initialCapacity]);
    }

    /**
     * If Array is full, this method copies the old array
     * to a new array with more positions
     */
    private void expandCapacity1() {
        stack = Arrays.copyOf(stack, stack.length + DEFAULT_CAPACITY);
    }


    /**
     * Method that adds more capacity to the array
     */
    public void expandCapacity2() {
        T[] stack2 = (T[]) (new Object[this.size() + DEFAULT_CAPACITY]);

        for (int i = 0; i < this.size(); i++) {
            stack2[i] = this.stack[i];
        }

        this.stack = stack2;
    }

    /**
     * Adds the specified element to the top of this stack,
     * expanding the capacity of the stack array if necessary.
     *
     * @param element generic element to be pushed onto stack
     */
    @Override
    public void push(T element) {
        if (size() == stack.length) {
            expandCapacity1();
            //expandCapacity2();
        }

        this.stack[top] = element;
        this.top++;
    }

    /**
     * Removes the element at the top of this stack
     * and returns a reference to it.
     * Throws an EmptyCollectionException if the stack is empty
     *
     * @return T element removed from the top of the stack
     * @throws EmptyCollectionException if a pop is attempted on
     *                                  a empty stack
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack is empty");
        }
        top--;
        T result = stack[top];
        stack[top] = null;

        return result;
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
            throw new EmptyCollectionException("Stack is empty");
        }
        return stack[top - 1];
    }

    /**
     * Method that returns true of the stack contains no elements
     * @return a boolean value if the stack is empty or not
     */
    @Override
    public boolean isEmpty() {
        return this.top == 0;
    }

    /**
     * Method that returns the number of elements in the stack
     * @return int number of elements in the stack
     */
    @Override
    public int size() {
        return this.top;
    }

    /**
     * Method that returns a string representation of the stack.
     * @return String representation of the stack
     */
    @Override
    public String toString() {
        String str = "";

        for(int i = 0; i < this.top; i++){
            str = str + this.stack[i] + " ";
        }

        return str + "";
    }
}
