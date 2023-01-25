package mygame.structures.heaps;


import mygame.exceptions.EmptyCollectionException;
import mygame.structures.binaryTree.ArrayBinaryTree;

public class ArrayHeap<T> extends ArrayBinaryTree<T> implements HeapADT<T> {

    // Default constructor
    public ArrayHeap() {
        super();
    }

    /**
     * Adds the specified object to this heap
     *
     * @param obj the element to be added to this heap
     */
    @Override
    public void addElement(T obj) {
        if (count == tree.length) {
            expandCapacity();
        }
        tree[count] = obj;
        count++;

        if (count > 1) {
            heapifyAdd();
        }
    }

    /**
     * Expand tree size when adding to full array
     */
    protected void expandCapacity() {
        T[] tmp = (T[]) (new Object[tree.length + tree.length]);
        for (int i = 0; i < tree.length; i++) {
            tmp[i] = tree[i];
        }
        tree = tmp;
    }

    /**
     * Reorders this heap to maintain the ordering property after
     * adding a node.
     */
    private void heapifyAdd() {
        T temp;
        int next = count - 1;

        temp = tree[next];

        while ((next != 0) && (((Comparable) temp).compareTo
                (tree[(next - 1) / 2]) < 0)) {

            tree[next] = tree[(next - 1) / 2];
            next = (next - 1) / 2;
        }

        tree[next] = temp;
    }

    /**
     * Removes the element with the lowest value in
     * this heap
     *
     * @return the element with the lowest value from this heap
     */
    @Override
    public T removeMin() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("The heap is empty");
        }

        T minElement = tree[0];
        tree[0] = tree[count - 1];
        heapifyRemove();
        // tree[count - 1] = null;
        count --;

        return minElement;
    }

    /**
     * Reorders this heap to maintain the ordering property.
     */
    private void heapifyRemove() {
        T temp;
        int node = 0;
        int left = 1;
        int right = 2;
        int next;

        if ((tree[left] == null) && (tree[right] == null)) {
            next = count;
        } else if (tree[left] == null) {
            next = right;
        } else if (tree[right] == null) {
            next = left;
        } else if (((Comparable)tree[left]).compareTo(tree[right]) < 0) {
            next = left;
        } else {
            next = right;
        }
        temp = tree[node];

        while ((next < count) && (((Comparable)tree[next]).compareTo(temp) < 0)) {

            tree[node] = tree[next];
            node = next;
            left = 2*node+1;
            right = 2*(node+1);

            if ((tree[left] == null) && (tree[right] == null)) {
                next = count;
            } else if (tree[left] == null) {
                next = right;
            } else if (tree[right] == null) {
                next = left;
            } else if (((Comparable)tree[left]).compareTo(tree[right]) < 0) {
                next = left;
            }else {
                next = right;
            }

        }
        tree[node] = temp;
    }

    /**
     * Returns a reference to the element with the lowest value in
     * this heap
     *
     * @return a reference to the element with the lowest value in this
     * heap
     */
    @Override
    public T findMin() {
        return tree[0];
    }
}
