package mygame.structures.classes;

import mygame.structures.interfaces.OrderedListADT;

public class DoubleLinkedOrderedList<T> extends DoubleLinkedList<T>
        implements OrderedListADT<T> {

    private void tailChecker() {
        if (tail.getNext() != null) {
            tail = tail.getNext();
        }
    }

    /**
     * Adds the specified element to this list at the proper location
     *
     * @param element the element to be added to this list
     */
    @Override
    public void add(T element) {
        if (!(element instanceof Comparable)) {
            return;
        }

        Comparable tmp = (Comparable) element;
        DoubleLinkedNode<T> newNode = new DoubleLinkedNode<>(element);

        if (head.getData() == null) {
            head.setData(element);

        } else if (tmp.compareTo(head.getData()) < 0) {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;

        } else {
            DoubleLinkedNode<T> n = head;
            DoubleLinkedNode<T> next;

            while (n.getNext() != null
                    && tmp.compareTo(n.getNext().getData()) > 0) {
                n = n.getNext();
            }

            next = n.getNext();
            newNode.setPrev(n);
            n.setNext(newNode);

            if (next != null) {
                newNode.setNext(next);
                next.setPrev(newNode);
            }

            this.tailChecker();
        }
        modCount++;
        counter++;
    }

}
