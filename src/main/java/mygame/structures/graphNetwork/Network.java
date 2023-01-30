package mygame.structures.graphNetwork;

import mygame.exceptions.BinaryTreeExceptions;
import mygame.exceptions.EmptyCollectionException;
import mygame.exceptions.GraphExceptions;
import mygame.exceptions.ListExceptions;
import mygame.structures.heaps.PriorityQueue;
import mygame.structures.lists.UnorderedArrayList;
import mygame.structures.lists.UnorderedListADT;
import mygame.structures.queues.LinkedQueue;
import mygame.structures.stacks.LinkedStack;

import java.util.Iterator;

public class Network<T> implements NetworkADT<T> {

    protected int numVertices;
    protected UnorderedArrayList<NetworkNode<T>> nodesList;

    /**
     * Network in list constructor
     */
    public Network() {
        this.numVertices = 0;
        this.nodesList = new UnorderedArrayList<>();
    }

    @Override
    public void addEdge(T vertex1, T vertex2, double weight) throws GraphExceptions {
        if (isEmpty()) {
            throw new GraphExceptions(GraphExceptions.EMPTY_GRAPH);
        }

        NetworkNode<T> node1 = this.getNode(vertex1);
        NetworkNode<T> node2 = this.getNode(vertex2);
        Edge<T> edgeNode = new Edge<>(node2, weight);

        node1.edgeList.addToRear(edgeNode);
    }

    /**
     * returns the node which has the target vertex
     *
     * @param targetVertex the target vertex
     * @return a network node
     * @throws GraphExceptions GraphExceptions
     */
    public NetworkNode<T> getNode(T targetVertex) throws GraphExceptions {
        boolean found = false;
        NetworkNode<T> node = null;
        Iterator<NetworkNode<T>> searchItr = nodesList.iterator();

        while (!found && searchItr.hasNext()) {
            NetworkNode<T> tmp = searchItr.next();
            if (tmp.element.equals(targetVertex)) {
                node = tmp;
                found = true;
            }
        }

        if (!found) {
            throw new GraphExceptions(GraphExceptions.ELEMENT_NOT_FOUND);
        }

        return node;
    }

    /**
     * finds and returns the last pair with the last
     * vertex from the shortes past and the cost to the vertex
     *
     * @param startVertex  the start vertex
     * @param targetVertex the target vertex
     * @return a pair with the final vertex and cost
     * @throws BinaryTreeExceptions BinaryTreeExceptions
     * @throws GraphExceptions      GraphExceptions
     */
    private Pair<T> findLastPairInShortestPair(T startVertex, T targetVertex) throws BinaryTreeExceptions, GraphExceptions, EmptyCollectionException, ListExceptions {
        PriorityQueue<Pair<T>> priorityQueue = new PriorityQueue<Pair<T>>();
        UnorderedListADT<T> verticesInPath = new UnorderedArrayList<>();
        Pair<T> startPair = new Pair<>(null, startVertex, 0.0);

        priorityQueue.addElement(startPair, (int) startPair.cost);

        while (!priorityQueue.isEmpty()) {
            Pair<T> pair = priorityQueue.removeNext();
            T vertex = pair.vertex;
            double minCostToVertex = pair.cost;

            if (vertex.equals(targetVertex)) {
                return pair;
            }

            verticesInPath.addToRear(vertex);

            for (Edge<T> tmpEdge : getNode(vertex).edgeList) {
                if (!verticesInPath.contains(tmpEdge.nodeTo.element)) {
                    double minCostToI = minCostToVertex + tmpEdge.weight;
                    Pair<T> tmpPair = new Pair<>(pair, tmpEdge.nodeTo.element, minCostToI);
                    priorityQueue.addElement(tmpPair, (int) tmpPair.cost);
                }
            }
        }

        throw new GraphExceptions(GraphExceptions.PATH_NOT_FOUND);
    }

    @Override
    public double shortestPathWeight(T vertex1, T vertex2) throws BinaryTreeExceptions, GraphExceptions, EmptyCollectionException, ListExceptions {
        try {
            getNode(vertex1);
        } catch (GraphExceptions ex) {
            throw new GraphExceptions(GraphExceptions.ELEMENT_NOT_FOUND);
        }

        return findLastPairInShortestPair(vertex1, vertex2).cost;
    }

    @Override
    public void addVertex(T vertex) {
        NetworkNode<T> node = new NetworkNode<>(vertex);
        nodesList.addToRear(node);
        numVertices++;
    }

    @Override
    public void removeVertex(T vertex) throws GraphExceptions, ListExceptions {
        if (isEmpty()) {
            throw new GraphExceptions(GraphExceptions.EMPTY_GRAPH);
        }

        NetworkNode<T> nodeToRemove = this.getNode(vertex);

        for (NetworkNode<T> nodeTemp : nodesList) {
            Iterator<Edge<T>> itEdge = nodeTemp.edgeList.iterator();
            UnorderedListADT<Edge<T>> found = new UnorderedArrayList<>();

            while (itEdge.hasNext()) {
                Edge<T> edgeTemp = itEdge.next();
                if (edgeTemp.nodeTo.equals(nodeToRemove)) {
                    found.addToRear(edgeTemp);
                }
            }

            for (Edge<T> tEdge : found) {
                nodeTemp.edgeList.remove(tEdge);
            }
        }

        nodesList.remove(nodeToRemove);
        numVertices--;
    }

    @Override
    public void addEdge(T vertex1, T vertex2) throws GraphExceptions {
        this.addEdge(vertex1, vertex2, 0);
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) throws GraphExceptions, ListExceptions {
        if (isEmpty()) {
            throw new GraphExceptions(GraphExceptions.EMPTY_GRAPH);
        }

        NetworkNode<T> node1 = this.getNode(vertex1);
        NetworkNode<T> node2 = this.getNode(vertex2);

        Iterator<Edge<T>> itEdge = node1.edgeList.iterator();
        Edge<T> found = null;

        while (itEdge.hasNext()) {
            Edge<T> edgeTemp = itEdge.next();
            if (edgeTemp.nodeTo.equals(node2)) {
                found = edgeTemp;
                break;
            }
        }

        node1.edgeList.remove(found);
    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex) throws EmptyCollectionException {
        LinkedQueue<NetworkNode<T>> traversalQueue = new LinkedQueue<>();
        UnorderedArrayList<T> resultList = new UnorderedArrayList<>();
        NetworkNode<T> nodeTemp, startNode;

        try {
            startNode = this.getNode(startVertex);
        } catch (GraphExceptions ex) {
            return resultList.iterator();
        }

        UnorderedArrayList<NetworkNode<T>> visited = new UnorderedArrayList<>();

        traversalQueue.enqueue(startNode);
        visited.addToRear(startNode);

        while (!traversalQueue.isEmpty()) {
            nodeTemp = traversalQueue.dequeue();
            resultList.addToRear(nodeTemp.element);

            for (Edge<T> nextNode : nodeTemp.edgeList) {
                if (!visited.contains(nextNode.nodeTo)) {
                    traversalQueue.enqueue(nextNode.nodeTo);
                    visited.addToRear(nextNode.nodeTo);
                }
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorDFS(T startVertex) throws EmptyCollectionException {
        LinkedStack<NetworkNode<T>> traversalStack = new LinkedStack<>();
        UnorderedArrayList<T> resultList = new UnorderedArrayList<>();
        NetworkNode<T> nodeTemp, startNode;
        boolean found;

        try {
            startNode = this.getNode(startVertex);
        } catch (GraphExceptions e) {
            return resultList.iterator();
        }

        UnorderedArrayList<NetworkNode<T>> visited = new UnorderedArrayList<>();

        traversalStack.push(startNode);
        resultList.addToRear(startNode.element);
        visited.addToRear(startNode);

        while (!traversalStack.isEmpty()) {
            nodeTemp = traversalStack.peek();
            found = false;

            Iterator<Edge<T>> itEdges = nodeTemp.edgeList.iterator();
            while (itEdges.hasNext() && !found) {
                Edge<T> nextNode = itEdges.next();
                if (!visited.contains(nextNode.nodeTo)) {
                    traversalStack.push(nextNode.nodeTo);
                    resultList.addToRear(nextNode.nodeTo.element);
                    visited.addToRear(nextNode.nodeTo);
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();

    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) throws EmptyCollectionException, ListExceptions {
        UnorderedArrayList<T> resultList = new UnorderedArrayList<>();

        try {
            getNode(startVertex);
        } catch (GraphExceptions ex) {
            return resultList.iterator();
        }

        Pair<T> lastPair = null;

        try {
            lastPair = findLastPairInShortestPair(startVertex, targetVertex);
            while (lastPair != null) {
                resultList.addToFront(lastPair.vertex);
                lastPair = lastPair.previous;
            }

            return resultList.iterator();
        } catch (GraphExceptions | BinaryTreeExceptions ex) {
            return resultList.iterator();
        }
    }

    private Pair<T> findLastPairInShortestPath(T startVertex, T targetVertex) throws GraphExceptions, EmptyCollectionException {
        PriorityQueue<Pair<T>> priorityQueue = new PriorityQueue<>();
        UnorderedArrayList<T> verticesInPath = new UnorderedArrayList<>();
        Pair<T> startPair = new Pair<>(null, startVertex, 0.0);

        priorityQueue.addElement(startPair, (int) startPair.cost);

        while (!priorityQueue.isEmpty()) {
            Pair<T> pair = priorityQueue.removeNext();
            T vertex = pair.vertex;
            double minCostToVertex = pair.cost;

            if (vertex.equals(targetVertex)) {
                return pair;
            }

            verticesInPath.addToRear(vertex);

            for (Edge<T> edgeTemp : getNode(vertex).edgeList) {
                if (!verticesInPath.contains(edgeTemp.nodeTo.element)) {
                    double minCostToI = minCostToVertex + edgeTemp.weight;
                    Pair<T> pairTemp = new Pair<>(pair, edgeTemp.nodeTo.element, minCostToI);
                    priorityQueue.addElement(pairTemp, (int) pairTemp.cost);
                }
            }
        }
        throw new GraphExceptions(GraphExceptions.PATH_NOT_FOUND);
    }

    @Override
    public boolean isEmpty() {
        return (numVertices == 0);
    }

    @Override
    public boolean isConnected() throws GraphExceptions, ListExceptions, EmptyCollectionException {
        if (isEmpty()) {
            throw new GraphExceptions(GraphExceptions.EMPTY_GRAPH);
        }

        Iterator<T> it = iteratorBFS(nodesList.first().element);
        int counter = 0;

        while (it.hasNext()) {
            it.next();
            counter++;
        }

        return (counter == numVertices);
    }

    @Override
    public int size() {
        return numVertices;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (NetworkNode<T> tNetworkNode : nodesList) {
            text.append(tNetworkNode.toString());
        }
        text.append("\n");
        return text.toString();
    }

    public int getNumVertices() {
        return this.numVertices;
    }

    public UnorderedListADT<NetworkNode<T>> getNodesList() {
        return this.nodesList;
    }
}
