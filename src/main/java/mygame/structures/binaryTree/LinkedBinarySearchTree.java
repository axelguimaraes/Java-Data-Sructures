package mygame.structures.binaryTree;


import mygame.exceptions.ElementNotFoundException;
import mygame.exceptions.EmptyListException;

public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    /**
     * Creates an empty binary search tree.
     */
    public LinkedBinarySearchTree() {
        super();
    }

    /**
     * Creates a binary search with the specified element as its root.
     *
     * @param element the element that will be the root of the new
     *                binary search tree
     */
    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    @Override
    public void addElement(T element) {

        BinaryTreeNode<T> temp = new BinaryTreeNode<T>(element);
        Comparable<T> comparableElement = (Comparable<T>) element;

        if (isEmpty()) {
            root = temp;
        } else {

            BinaryTreeNode<T> current = root;
            boolean added = false;

            while (!added) {
                if (comparableElement.compareTo(current.element) < 0) {

                    if (current.left == null) {
                        current.left = temp;
                        added = true;
                    } else {
                        current = current.left;
                    }

                } else {

                    if (current.right == null) {
                        current.right = temp;
                        added = true;
                    } else {
                        current = current.right;
                    }

                }
            }
        }
        count++;
    }

    @Override
    public T removeElement(T targetElement) throws ElementNotFoundException, EmptyListException {
        T result = null;
        if (!isEmpty()) {

            if (((Comparable) targetElement).equals(root.element)) {
                result = root.element;
                root = replacement(root);
                count--;
            } else {
                BinaryTreeNode<T> current, parent = root;
                boolean found = false;

                if (((Comparable) targetElement).compareTo(root.element) < 0) {
                    current = root.left;
                } else {
                    current = root.right;
                }

                while (current != null && !found) {

                    if (targetElement.equals(current.element)) {

                        found = true;
                        count--;
                        result = current.element;

                        if (current == parent.left) {
                            parent.left = replacement(current);
                        } else {
                            parent.right = replacement(current);
                        }
                    } else {
                        parent = current;

                        if (((Comparable) targetElement).compareTo(current.element) < 0) {
                            current = current.left;
                        } else {
                            current = current.right;
                        }
                    }
                } //while

                if (!found) {
                    throw new ElementNotFoundException("Not found in binary search tree");
                }
            }
        } else {// end outer if
            throw new EmptyListException("list is empty");
        }
        return result;
    }

    /**
     * Returns a reference to a node that will replace the one
     * specified for removal. In the case where the removed node has
     * two children, the inorder successor is used as its replacement.
     *
     * @param node the node to be removeed
     * @return a reference to the replacing node
     */
    private BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result = null;
        // Quando o node é uma folha
        if ((node.left == null) && (node.right == null)) {
            result = null;
            // Quando o node só tem um filho á esquerda, o node é subtituido pelo seu filho
        } else if ((node.left != null) && (node.right == null)) {
            result = node.left;
            // Quando o node só tem um filho á direita, o node é subtituido pelo seu filho
        } else if ((node.left == null) && (node.right != null)) {
            result = node.right;

        } else {

            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;
            // Chegar ao final do filho direito do lado esquerdo
            while (current.left != null) {
                parent = current;
                current = current.left;
            }
            // se o node sem filho á esquerda for o igual ao filho direito do node a remover o filho esquerdo passa a ser filho esquerdo do filho direito
            if (node.right == current) {
                current.left = node.left;
                // se o node filho da direita tiver nodes á esquerda, esse node substituirá o node removido e o node pai dele passará a ser seu filho á direita
            } else {
                parent.left = current.right;
                current.right = node.right;
                current.left = node.left;
            }

            result = current;
        }
        return result;
    }

    @Override
    public void removeAllOccurrences(T targetElement) throws ElementNotFoundException, EmptyListException {
        removeElement(targetElement);

        try{
            while(true){
                removeElement(targetElement);
            }
        } catch(Exception e) {}

    }

    @Override
    public T removeMin() throws EmptyListException, ElementNotFoundException {
        return removeElement(findMin());
    }

    @Override
    public T removeMax() throws ElementNotFoundException, EmptyListException {
        return  removeElement(findMax());
    }

    @Override
    public T findMin() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("Empty List");
        }

        BinaryTreeNode<T> current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current.element;
    }

    @Override
    public T findMax() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("Empty List");
        }

        BinaryTreeNode<T> current = root;

        while (current.right != null) {
            current = current.right;
        }

        return current.element;
    }
}
