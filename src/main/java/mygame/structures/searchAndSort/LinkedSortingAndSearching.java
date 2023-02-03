package mygame.structures.searchAndSort;

import mygame.structures.classes.DoubleLinkedList;
import mygame.structures.classes.DoubleLinkedNode;

import java.util.Iterator;

public class LinkedSortingAndSearching<T> {

    public static <T extends Comparable<? super T>> boolean linearSearch(DoubleLinkedList<T> data, T target) {
        boolean found = false;

        Iterator<T> it = data.iterator();

        while (it.hasNext()) {
            T temp = it.next();
            if (temp.compareTo(target) == 0) {
                found = true;
            }
        }
        return found;
    }

    public static <T extends Comparable<? super T>> boolean binarySearch(DoubleLinkedList<T> list, T target) {
        DoubleLinkedNode<T> start = list.getHead();
        DoubleLinkedNode<T> last = null;
        boolean found = false;

        do {
            DoubleLinkedNode<T> mid = middleNode(start, last);

            if (mid == null) {
                throw new NullPointerException();
            }

            if (mid.getData() == target) {
                found = true;
            } else if (mid.getData().compareTo(target) > 0) {
                last = mid;
            } else {
                start = mid.getNext();
            }

        } while (last == null || last != start);

        return found;
    }

    private static <T extends Comparable<? super T>> DoubleLinkedNode<T> middleNode(DoubleLinkedNode<T> start, DoubleLinkedNode<T> last) {
        if (start == null)
            return null;

        DoubleLinkedNode<T> slow = start;
        DoubleLinkedNode<T> fast = start.getNext();

        while (fast != last) {
            fast = fast.getNext();
            if (fast != last) {
                slow = slow.getNext();
                fast = fast.getNext();
            }
        }
        return slow;
    }
}
