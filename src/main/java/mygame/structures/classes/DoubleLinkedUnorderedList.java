package mygame.structures.classes;

import mygame.structures.interfaces.UnorderedListADT;

public class DoubleLinkedUnorderedList<T> extends DoubleLinkedList<T>
        implements UnorderedListADT<T> {

    /**
     * Adds an element to the front of the list.
     *
     * @param element the element to be added
     */
    @Override
    public void addToFront(T element) {
        if (head.getData() == null) {
            head.setData(element);

        } else {
            DoubleLinkedNode<T> tmp = head;
            head = new DoubleLinkedNode<>(element);
            head.setNext(tmp);
        }

        counter++;
        modCount++;
    }

    /**
     * Adds an element to the rear of the list.
     *
     * @param element the element to be added
     */
    @Override
    public void addToRear(T element) {
        if (head == null) {
            head.setData(element);

        } else {
            DoubleLinkedNode<T> newTail = new DoubleLinkedNode<>(element);
            tail.setNext(newTail);
            newTail.setPrev(tail);
            tail = tail.getNext();
        }

        counter++;
        modCount++;
    }

    /**
     * Adds an element after an element of the list.
     *
     * @param element the element to be added
     * @param previous the previous element of the new element.
     */
    @Override
    public void addAfter(T element, T previous) {
        if (!contains(previous)) {
            return;
        }

        DoubleLinkedNode<T> previousNode = returnsNodeByElement(previous);
        DoubleLinkedNode<T> nextNode = previousNode.getNext();
        DoubleLinkedNode<T> newNode = new DoubleLinkedNode<>(element);
        newNode.setPrev(previousNode);
        previousNode.setNext(newNode);

        if (nextNode != null) {
            newNode.setNext(nextNode);
            nextNode.setPrev(newNode);
        }

        counter++;
        modCount++;
    }

}
