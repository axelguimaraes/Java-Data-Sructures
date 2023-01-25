package mygame.structures.binaryTree;

import mygame.exceptions.ElementNotFoundException;
import mygame.structures.lists.UnorderedArray;

import java.util.Iterator;

public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {
    protected int count;
    protected T[] tree;
    private final int CAPACITY = 50;

    /**
     * Creates an empty binary tree
     */
    public ArrayBinaryTree(){
        this.count = 0;
        this.tree = (T[]) new Object[CAPACITY];
    }

    /**
     * Creates a binary tree with the specified element as its root
     * @param element the element which will become the root of the ney tree
     */
    public ArrayBinaryTree(T element){
        this.count = 1;
        this.tree = (T[]) new Object[CAPACITY];
        this.tree[0] = element;
    }

    /**
     * Returns a reference to the root element
     * @return a reference to the root
     */
    @Override
    public T getRoot() {
        return this.tree[0];
    }

    /**
     * Returns true if this binary tree is empty and false otherwise
     * @return true if this binary tree is empty
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * Returns the number of elements in this binary tree
     * @return ihe integer number of elements in this tree
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Returns true if the binaru tree contains an element that matches the
     * specified element and false otherwise
     * @param targetElement the element being sought in the tree
     * @return true if the tree contains the target element
     */
    @Override
    public boolean contains(T targetElement) {
        for (int ct = 0; ct < this.count; ct++) {
            if (targetElement.equals(this.tree[ct])) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a reference to the specified target element if it is found in this
     * binary tree. Throws a NoSuchElementException if the specified target element
     * is not found in the binary tree
     * @param targetElement the element being sought in the tree
     * @returntrue if the element is in the tree
     * @throws ElementNotFoundException if an element not found exception occurs
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        T temp = null;
        boolean found = false;

        for (int ct = 0; ct < this.count && !found; ct++) {
            if (targetElement.equals(this.tree[ct])) {
                found = true;
                temp = this.tree[ct];
            }
        }

        if (!found) {
            throw new ElementNotFoundException("Binary tree");
        }

        return temp;
    }

    protected int findAgain(T targetElement, int next) {

        if (tree[next] == null) {
            return -1;
        }

        if (tree[next].equals(targetElement)) {
            return next;
        }

        int temp = findAgain(targetElement, (next * 2 + 1));

        if (temp == -1) {
            temp = findAgain(targetElement, (next * 2 + 2));
        }

        return temp;
    }

    /**
     * Performs an inorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with the root
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        UnorderedArray<T> templist = new UnorderedArray<>();
        inorder(0, templist);

        return templist.iterator();
    }

    /**
     * Performs a recursive inorder traversal
     * @param node the node used in the traversal
     * @param templist
     */
    protected void inorder(int node, UnorderedArray<T> templist) {
        if (node < this.tree.length) {
            if (this.tree[node] != null) {
                inorder(node * 2 + 1, templist);
                templist.addToRear(this.tree[node]);
                inorder((node + 1) * 2, templist);
            }
        }
    }

    /**
     * Performs a preorder traversal on this binary tree by calling an
     * overloaded, recursive preorder method that starts with the root
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        UnorderedArray<T> templist = new UnorderedArray<>();
        preOrder(0, templist);

        return templist.iterator();
    }

    /**
     * Executa um percurso de pré-encomenda recursiva.
     * @param node
     * @param templist
     */
    protected void preOrder(int node, UnorderedArray<T> templist) {
        if (node < this.tree.length) {
            if (this.tree[node] != null) {
                templist.addToRear(this.tree[node]);
                inorder((node + 1) * 2 - 1, templist);
                inorder((node + 1) * (2 + 1) - 1, templist);
            }
        }
    }

    /**
     * Performs a postorder traversal on this binary tree by calling an
     * overloaded, recursive postorder method that starts with the root
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        UnorderedArray<T> templist = new UnorderedArray<>();
        postOrder(0, templist);

        return templist.iterator();
    }

    /**
     * Executa uma passagem recursiva de ordem posterior
     * @param node
     * @param templist
     */
    protected void postOrder(int node, UnorderedArray<T> templist) {
        if (node < this.tree.length) {
            if (this.tree[node] != null) {
                inorder((node + 1) * 2 - 1, templist);
                inorder((node + 1) * (2 + 1) - 1, templist);
                templist.addToRear(this.tree[node]);
            }
        }
    }

    /**
     * Performs a levelorder traversal on this binary tree, using a queue
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
        UnorderedArray<T> templist = new UnorderedArray<>();

        for (int ct = 0; ct < this.count; ct++) {
            templist.addToRear(this.tree[ct]);
        }

        return templist.iterator();
    }
}