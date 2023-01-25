package mygame.structures.graphNetwork;

import mygame.exceptions.BinaryTreeExceptions;
import mygame.exceptions.EmptyCollectionException;
import mygame.exceptions.GraphExceptions;
import mygame.exceptions.ListExceptions;

/**
 * NetworkADT defines the interface to a network.
 */
public interface NetworkADT<T> extends GraphADT<T> {

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight  the weight
     * @throws GraphExceptions GraphExceptions
     */
    public void addEdge(T vertex1, T vertex2, double weight) throws GraphExceptions, GraphExceptions;

    /**
     * Returns the weight of the shortest path in this network.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the weight of the shortest path in this network
     * @throws BinaryTreeExceptions BinaryTreeExceptions
     * @throws GraphExceptions GraphExceptions
     * @throws EmptyCollectionException
     */
    public double shortestPathWeight(T vertex1, T vertex2) throws BinaryTreeExceptions, GraphExceptions, EmptyCollectionException, ListExceptions, BinaryTreeExceptions, EmptyCollectionException, ListExceptions;
}
