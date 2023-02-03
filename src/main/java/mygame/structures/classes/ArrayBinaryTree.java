package mygame.structures.classes;

import mygame.structures.exceptions.ElementNotFoundException;
import mygame.structures.interfaces.BinaryTreeADT;

import java.util.Iterator;

public class ArrayBinaryTree<T> implements BinaryTreeADT<T>
{
    protected int count;
    protected T[] tree;
    private final int capacity = 50;

    /**
     * Creates an empty binary tree
     */
    public ArrayBinaryTree()
    {
        count = 0;
        tree = (T[]) new Object[capacity];
    }

    /**
     * Creates a binary tree with the specified element as its root
     * @param element root of the tree
     */
    public ArrayBinaryTree (T element)
    {
        count = 1;
        tree = (T[]) new Object[capacity];
        tree[0] = element;
    }

    /**
     * Expands capacity when full
     */
    protected void expandCapacity()
    {
        T[] temp = (T[]) new Object[tree.length * 2];

        for (int ct=0; ct < tree.length; ct++)
            temp[ct] = tree[ct];

        tree = temp;
    }

    /**
     * Removes the left subtree of this binary tree.
     */
    public void removeLeftSubtree()
    {
    }

    /**
     * Removes the right subtree of this binary tree.
     */
    public void removeRightSubtree()
    {
    }

    /**
     * Deletes all nodes from this binary tree.
     */
    public void removeAllElements()
    {
        count = 0;

        for (int ct=0; ct<tree.length; ct++)
            tree[ct] = null;
    }

    /**
     * Checks if the binary tree is empty or not
     * @return true if it's empty; false if it's not empty
     */
    public boolean isEmpty()
    {
        return (count == 0);
    }

    /**
     * Getter for the binary tree size
     * @return size
     */
    public int size()
    {
        return count;
    }


    /**
     * Checks if the binary tree contains an element
     * @param targetElement element to be checked
     * @return true if it contains; false if it doesn't contain
     */
    public boolean contains (T targetElement)
    {
        boolean found = false;

        for (int ct=0; ct<count && !found; ct++)
            if (targetElement.equals(tree[ct]))
                found = true;

        return found;
    }

    /**
     * Retrieves a reference to the specific target element
     * @param targetElement target to be retrieved
     * @return reference to the target element
     * @throws ElementNotFoundException thrown if the element is not found
     */
    public T find (T targetElement) throws ElementNotFoundException
    {
        T temp=null;
        boolean found = false;

        for (int ct=0; ct<count && !found; ct++)
            if (targetElement.equals(tree[ct]))
            {
                found = true;
                temp = tree[ct];
            }

        if (!found)
            throw new ElementNotFoundException("binary tree");

        return temp;
    }

    /**
     * Returns a string representation of this binary tree.
     * @return {@link String} representation of this binary tree.
     */
    public String toString()
    {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        inorder (0, templist);

        return templist.toString();
    }

    /**
     * Performs an inorder traversal on the binary tree by calling an overloaded, recursive inorder method that
     * starts with the root
     * @return {@link Iterator iterator} in-order
     */
    public Iterator<T> iteratorInOrder()
    {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        inorder (0, templist);

        return templist.iterator();
    }

    /**
     * Performs a recursive inorder traversal
     * @param node root node
     * @param templist list of nodes
     */
    protected void inorder (int node, ArrayUnorderedList<T> templist)
    {
        if (node < tree.length)
            if (tree[node] != null)
            {
                inorder (node*2+1, templist);
                templist.addToRear(tree[node]);
                inorder ((node+1)*2, templist);
            }
    }

    /**
     * Performs a preorder traversal on the binary tree by calling an overloaded, recursive preorder method that starts
     * with the root
     * @return {@link Iterator iterator} pre-order
     */
    public Iterator<T> iteratorPreOrder()
    {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        preorder (0, templist);

        return templist.iterator();
    }

    /**
     * Performs a recursive preorder traversal
     * @param node root node
     * @param templist list of nodes
     */
    protected void preorder (int node, ArrayUnorderedList<T> templist)
    {
        if (node < tree.length)
            if (tree[node] != null)
            {
                templist.addToRear(tree[node]);
                inorder (node*2+1, templist);
                inorder ((node+1)*2, templist);
            }
    }

    /**
     * Performs a postorder traversal on the binary tree by calling an overloded, recursive postorder method that starts
     * with the root
     * @return {@link Iterator iterator} post-order
     */
    public Iterator<T> iteratorPostOrder()
    {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        postorder (0, templist);

        return templist.iterator();
    }

    /**
     * Performs a recursive postorder traversal
     * @param node root node
     * @param templist list of nodes
     */
    protected void postorder (int node, ArrayUnorderedList<T> templist)
    {
        if (node < tree.length)
            if (tree[node] != null)
            {
                inorder (node*2+1, templist);
                inorder ((node+1)*2, templist);
                templist.addToRear(tree[node]);
            }
    }

    /**
     * Performs a levelorder traversal on the binary tree, using a temporary {@link ArrayUnorderedList list}
     * @return {@link Iterator iterator} level-order
     */
    public Iterator<T> iteratorLevelOrder()
    {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        int ct = 0; // current number of elements added to list
        int i = 0; // current position in array

        while (ct < count)
        {
            if (tree[i] != null)
            {
                tempList.addToRear(tree[i]);
                ct++;
            }
            i++;
        }

        return tempList.iterator();
    }
}