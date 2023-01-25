package mygame.structures.lists;

public class UnorderedDoubleLinkedList<T> extends DoubleLinkedList<T> implements UnorderedDoubleLinkedListADT<T> {

    /**
     * Adds a element to the front
     * @param element element to add
     */
    @Override
    public void addToFront(T element) {
        DoubleNode<T> newNode = new DoubleNode<>(element);

        if (isEmpty()) {
            head = newNode;
            tail = head;
        } else {
            DoubleNode<T> tmp = head;
            head = newNode;
            newNode.setNext(tmp);
            tmp.setPrevious(head);
        }
        size++;
        modCount++;
    }

    /**
     * Adds a element to the rear
     * @param element element to add
     */
    @Override
    public void addToRear(T element) {
        DoubleNode<T> newNode = new DoubleNode<>(element);

        if (isEmpty()) {
            addToFront(element);
        } else {
            DoubleNode<T> tmp = tail;
            tail = newNode;
            newNode.setPrevious(tmp);
            tmp.setNext(newNode);

            size++;
            modCount++;
        }
    }

    /**
     * Adds a element after another
     * @param element element to add
     * @param target the target
     */
    @Override
    public void addAfter(T element, T target) {
        boolean found = false;
        DoubleNode<T> current = head;

        while (current != null && found == false) {
            if (current.getElement().equals(target)) {
                found = true;
            } else {
                current = current.getNext();
            }
        }
        if (found == true) {
            if (current != tail) {
                DoubleNode<T> newNode = new DoubleNode<>(element);
                DoubleNode<T> nextToCurrent = current.getNext();

                newNode.setNext(nextToCurrent);
                newNode.setPrevious(current);
                current.setNext(newNode);
                nextToCurrent.setPrevious(newNode);

                size++;
                modCount++;
            } else {
                addToRear(element);
            }
        } else {
            System.out.println("O current é o tail");
        }
    }
}
