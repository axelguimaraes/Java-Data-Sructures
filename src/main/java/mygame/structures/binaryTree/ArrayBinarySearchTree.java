package mygame.structures.binaryTree;

import mygame.exceptions.ElementNotFoundException;
import mygame.exceptions.EmptyListException;
import mygame.structures.lists.UnorderedArrayList;

import java.util.Iterator;


/**
 * ArrayBinarySearchTree implements a binary search tree using an array
 */
public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T> {
    protected int height;
    protected int maxIndex;
    protected int size;

    /**
     * Creates an empty binary search tree
     */
    public ArrayBinarySearchTree() {
        super();
        height = 0;
        maxIndex = -1;
    }

    /**
     * Creates a binary search with the specified element as its root
     *
     * @param element the element that will become the root of the new tree
     */
    public ArrayBinarySearchTree (T element) {
        super(element);
        height = 0;
        maxIndex = -1;
    }

    /**
     * Adds the specified object to this binary search tree in the
     * appropriate position according to its key value. Note that
     * equal elements are added to the right. Also note that the
     * index of the left child of the current index can be found by
     * doubling the current index and adding 1. Finding the index of the
     * right child can be calculated by doubling the current index and adding 2
     *
     * @param element the element to be added to the search tree
     */
    @Override
    public void addElement(T element) {

        if (tree.length < maxIndex * 2 + 3) {
            expandCapacity();
        }

        Comparable<T> tempelement = (Comparable<T>) element;

        if (isEmpty()) {
            tree[0] = element;
            maxIndex = 0;
        } else {

            boolean added = false;
            int currentIndex = 0;

            while (!added) {
                if (tempelement.compareTo((tree[currentIndex])) < 0) {
                    /** go left */
                    if (tree[currentIndex * 2 + 1] == null) {
                        tree[currentIndex * 2 + 1] = element;
                        added = true;
                        if (currentIndex * 2 + 1 > maxIndex) {
                            maxIndex = currentIndex * 2 + 1;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 1;
                    }
                } else {
                    /** go right */
                    if (tree[currentIndex * 2 + 2] == null) {
                        tree[currentIndex * 2 + 2] = element;
                        added = true;
                        if (currentIndex * 2 + 2 > maxIndex) {
                            maxIndex = currentIndex * 2 + 2;
                        }
                    } else {
                        currentIndex = currentIndex * 2 + 2;
                    }
                }
            }
        }
        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        count++;
    }

    /**
     * Method to expand the capacity
     */
    private void expandCapacity() {
        T[] tmp = (T[]) (new Object[tree.length + tree.length]);
        for (int i = 0; i < tree.length; i++) {
            tmp[i] = tree[i];
        }
        tree = tmp;
    }

    @Override
    public T removeElement(T targetElement) throws EmptyListException, ElementNotFoundException {
        if (isEmpty()) {
            throw new EmptyListException("Empty list");
        }

        int position = findAgain(targetElement, 0);

        if (position == -1) {
            throw new ElementNotFoundException("Element not found");
        }

        T tmp = tree[position];
        replacement(position);

        int temp = maxIndex;
        maxIndex = -1;

        for (int i = 0; i <= temp; i++) {
            if (tree[i] != null) {
                maxIndex = i;
            }
        }

        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        return tmp;

    }

    protected void replacement(int position) {
        if((position * 2 + 1) <= maxIndex && tree[(position * 2 + 1)] != null){
            UnorderedArrayList<T> list = new UnorderedArrayList<>();
            getElementsAndClean(position, list);

            Iterator<T> tmpItr = list.iterator();
            tmpItr.next();

            tree[position] = tmpItr.next();
            size++;

            while (tmpItr.hasNext()) {
                this.addElement(tmpItr.next());
            }
        } else if (((position + 1) * 2) <= maxIndex && tree[((position + 1) * 2)] != null){
            UnorderedArrayList<T> list = new UnorderedArrayList<>();
            getElementsAndClean(position, list);

            Iterator<T> tmpItr = list.iterator();
            tmpItr.next();

            tree[position] = tmpItr.next();
            size++;

            while (tmpItr.hasNext()) {
                this.addElement(tmpItr.next());
            }
        } else {
            tree[position] = null;
            size--;
        }

    }

    private void getElementsAndClean(int position, UnorderedArrayList<T> list) {

        if (position < tree.length) {
            if (tree[position] != null) {
                list.addToRear(tree[position]);
                tree[position] = null;
                size--;
                getElementsAndClean(position * 2 + 1, list);
                getElementsAndClean((position + 1) * 2, list);
            }
        }

    }

    @Override
    public void removeAllOccurrences(T targetElement) throws ElementNotFoundException, EmptyListException {
        removeElement(targetElement);

        try {
            while (true) {
                removeElement(targetElement);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public T removeMin() throws EmptyListException, ElementNotFoundException {
        return removeElement(findMin());
    }

    @Override
    public T removeMax() throws EmptyListException, ElementNotFoundException {
        return removeElement(findMax());
    }

    @Override
    public T findMin() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("Empty List");
        }

        int min = 0;

        while (tree[2 * min + 1] != null) {
            min = 2 * min + 1;
        }

        return tree[min];
    }

    @Override
    public T findMax() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("Empty List");
        }

        int max = 0;

        while (tree[2 * max + 2] != null) {
            max = 2 * max + 2;
        }

        return tree[max];

    }
}
