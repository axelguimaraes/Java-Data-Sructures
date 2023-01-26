package mygame.game;

import mygame.exceptions.EmptyCollectionException;
import mygame.exceptions.GraphExceptions;
import mygame.structures.graphNetwork.Edge;
import mygame.structures.graphNetwork.Network;
import mygame.structures.graphNetwork.NetworkNode;
import mygame.structures.lists.UnorderedListADT;
import mygame.structures.stacks.LinkedStack;

import java.util.Iterator;

public class GameMap {

    protected Network<Local> mapGraph;
    private UnorderedListADT<PlayerPosition> playerPositions;

    public GameMap() {
        this.mapGraph = new Network<>();
        this.mapGraph.addVertex(new Portal("Start", 9999, new Coordinates(123123, 123123),null, 9999));
    }


    /**
     * adds a new location to the game map
     *
     * @param location the location to add
     */
    public void addLocation(Local location) {
        this.mapGraph.addVertex(location);
    }

    /**
     * connects two locations in the game map
     * with a specified weight
     *
     * @param location1 the first location
     * @param location2 the second location
     * @param weight    the weight of the connection
     * @throws GraphExceptions GraphExceptions
     */
    public void connectLocations(Local location1, Local location2, double weight) throws GraphExceptions {
        this.mapGraph.addEdge(location1, location2, weight);
    }

    /**
     * Finds the shortest path between two specified locations in the graph.
     *
     * @param startLocation The starting location for the shortest path calculation.
     * @param endLocation The ending location for the shortest path calculation.
     * @return A stack containing the nodes in the shortest path.
     * @throws GraphExceptions if the start or end location is not found in the graph.
     * @throws EmptyCollectionException when the stack is empty and an operation is performed on it.
     */
    public LinkedStack<Local> findShortestPath(Local startLocation, Local endLocation) throws GraphExceptions, EmptyCollectionException {
        LinkedStack<Local> path = new LinkedStack<>();
        boolean found = false;
        Iterator<NetworkNode<Local>> itr = mapGraph.getNodesList().iterator();
        while (itr.hasNext() && !found) {
            NetworkNode<Local> currentNode = itr.next();
            if (currentNode.getElement().equals(startLocation)) {
                found = true;
                currentNode.setVisited(true);
                path.push(currentNode.getElement());
                if (!currentNode.getElement().equals(endLocation)) {
                    findShortestPathRecursive(currentNode, endLocation, path);
                }
            }
        }
        if (!found) {
            throw new GraphExceptions(GraphExceptions.ELEMENT_NOT_FOUND);
        }
        return path;
    }

    /**
     * Finds the shortest path between the current node and the specified end location using a recursive approach.
     * The path is stored in the provided stack.
     *
     * @param currentNode  The current node being evaluated in the recursive call.
     * @param endLocation  The end location for which the shortest path is being calculated.
     * @param path         A stack to store the nodes in the shortest path.
     * @throws EmptyCollectionException when the stack is empty and an operation is performed on it.
     */
    private void findShortestPathRecursive(NetworkNode<Local> currentNode, Local endLocation, LinkedStack<Local> path) throws EmptyCollectionException {
        Iterator<Edge<Local>> itr = currentNode.getEdgeList().iterator();
        double minCost = Double.MAX_VALUE;
        NetworkNode<Local> nextNode = null;
        while (itr.hasNext()) {
            Edge<Local> edge = itr.next();
            if (!edge.getNodeTo().isVisited() && edge.getWeight() < minCost) {
                minCost = edge.getWeight();
                nextNode = edge.getNodeTo();
            }
        }
        if (nextNode != null) {
            nextNode.setVisited(true);
            path.push(nextNode.getElement());
            if (!nextNode.getElement().equals(endLocation)) {
                findShortestPathRecursive(nextNode, endLocation, path);
            }
        } else {
            path.pop();
        }
    }

    /**
     * returns an iterator that traverses the game map
     * using depth-first search
     *
     * @param startLocation the starting location
     * @return an iterator
     */
    public Iterator<Local> depthFirstIterator(Local startLocation) throws EmptyCollectionException {
        return this.mapGraph.iteratorDFS(startLocation);
    }

    /**
     * returns an iterator that traverses the game map
     * using breadth-first search
     *
     * @param startLocation the starting location
     * @return an iterator
     */
    public Iterator<Local> breadthFirstIterator(Local startLocation) throws EmptyCollectionException {
        return this.mapGraph.iteratorBFS(startLocation);
    }

    public String toString() {
        return this.mapGraph.toString();
    }
}
