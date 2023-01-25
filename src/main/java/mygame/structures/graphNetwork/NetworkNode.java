package mygame.structures.graphNetwork;

import mygame.structures.lists.UnorderedArray;
import mygame.structures.lists.UnorderedListADT;

import java.util.Iterator;

public class NetworkNode<T> {

    protected T element;
    protected UnorderedListADT<Edge<T>> edgeList;
    protected boolean visited;

    public NetworkNode(T element) {
        this.element = element;
        this.edgeList = new UnorderedArray<>();
        this.visited = false;
    }

    @Override
    public String toString() {
        String text = "\nElement: " + element + "";
        Iterator<Edge<T>> printItr = edgeList.iterator();
        while (printItr.hasNext()) {
            Edge<T> tmpNode = printItr.next();
            text += "\nEdge: " + element + "->" + tmpNode.nodeTo.element + " Weight :" + tmpNode.weight;
        }
        return text;
    }

    public T getElement() {
        return this.element;
    }

    public UnorderedListADT<Edge<T>> getEdgeList() {
        return this.edgeList;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
