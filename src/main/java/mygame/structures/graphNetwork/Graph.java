package mygame.structures.graphNetwork;

import mygame.exceptions.BinaryTreeExceptions;
import mygame.exceptions.EmptyCollectionException;
import mygame.exceptions.GraphExceptions;
import mygame.exceptions.ListExceptions;
import mygame.structures.lists.UnorderedArrayList;
import mygame.structures.queues.LinkedQueue;
import mygame.structures.stacks.LinkedStack;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Graph<T> implements GraphADT<T> {
    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices;
    protected boolean[][] adjMatrix;
    public T[] vertices;

    public Graph() {
        this.numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    @Override
    public void addVertex(T vertex) {
        if (this.numVertices == this.vertices.length) {
            expandCapacity();
        }

        this.vertices[this.numVertices] = vertex;

        for (int i = 0; i <= this.numVertices; i++) {
            this.adjMatrix[this.numVertices][i] = false;
            this.adjMatrix[i][this.numVertices] = false;
        }

        this.numVertices++;
    }

    @Override
    public void removeVertex(T vertex) {
        for (int i = 0; i < this.numVertices; i++) {
            if (vertex.equals(this.vertices[i])) {
                removeVertex(i);
            }
        }
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    @Override
    public Iterator iteratorBFS(T startVertex) throws EmptyCollectionException {
        return iteratorBFS(this.getIndex(startVertex));
    }

    @Override
    public Iterator iteratorDFS(T startVertex) throws EmptyCollectionException {
        return iteratorDFS(this.getIndex(startVertex));
    }

    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) throws BinaryTreeExceptions, GraphExceptions, ListExceptions, EmptyCollectionException {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isConnected() throws GraphExceptions, ListExceptions {
        if (isEmpty()) {
            return false;
        }

        Iterator<T> it = null;
        try {
            it = iteratorBFS(0);
        } catch (EmptyCollectionException ex) {
            Logger.getLogger(Graph.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;

        while (it.hasNext()) {
            it.next();
            count++;
        }
        return count == this.numVertices;
    }

    @Override
    public int size() {
        return this.numVertices;
    }

    public void addEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = true;
            this.adjMatrix[index2][index1] = true;
        }
    }

    public void removeVertex(int index) {
        if (indexIsValid(index)) {
            this.numVertices--;
        }

        for (int i = index; i < this.numVertices; i++) {
            this.vertices[i] = this.vertices[i +1];
        }

        for (int i = index; i < this.numVertices; i++) {
            for (int j = 0; j <= this.numVertices; j++) {
                this.adjMatrix[i][j] = this.adjMatrix[i+1][j];
            }
        }

        for (int i = index; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++) {
                this.adjMatrix[j][i] = this.adjMatrix[j][i + 1];
            }
        }
    }

    private void expandCapacity() {
        T[] verticesTmp = ((T[]) new Object[this.vertices.length * 2]);

        boolean[][] adjMaxtrixTmp = new boolean[this.vertices.length * 2][this.vertices.length * 2];

        for (int i = 0; i < this.vertices.length; i++) {
            for (int j = 0; j < this.vertices.length; j++) {
                adjMaxtrixTmp[i][j] = this.adjMatrix[i][j];
            }
            verticesTmp[i] = this.vertices[i];
        }

        this.vertices = verticesTmp;
        this.adjMatrix = adjMaxtrixTmp;
    }

    protected boolean indexIsValid(int index) {
        return index != -1;
    }

    protected int getIndex(T vertex) {
        for (int i = 0; i < this.vertices.length; i++) {
            if (this.vertices[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            this.adjMatrix[index1][index2] = false;
            this.adjMatrix[index2][index1] = false;
        }
    }

    public Iterator iteratorBFS(int startIndex) throws EmptyCollectionException {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        UnorderedArrayList<T> resultList = new UnorderedArrayList<>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(this.vertices[x]);

            for (int i = 0; i < this.numVertices; i++) {
                if (this.adjMatrix[x][i] && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    public Iterator iteratorDFS(int startIndex) throws EmptyCollectionException {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        UnorderedArrayList<T> resultList = new UnorderedArrayList<>();
        boolean[] visited = new boolean[this.numVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(this.vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            for (int i = 0; (i < this.numVertices) && !found; i++) {
                if (this.adjMatrix[x][i] && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(this.vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }

            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) throws EmptyCollectionException, EmptyCollectionException {
        UnorderedArrayList<T> resultList = new UnorderedArrayList<>();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return resultList.iterator();
        }
        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);
        while (it.hasNext()) {
            resultList.addToRear(this.vertices[it.next()]);
        }
        return resultList.iterator();
    }

    private Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) throws EmptyCollectionException, EmptyCollectionException {
        int index = startIndex;
        int[] pathLength = new int[this.numVertices];
        int[] predecessor = new int[this.numVertices];
        LinkedQueue<Integer> trasversalQueue = new LinkedQueue<>();
        UnorderedArrayList<Integer> resultList = new UnorderedArrayList<>();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || (startIndex == targetIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[this.numVertices];

        for (int i = 0; i < this.numVertices; i++) {
            visited[i] = false;
        }

        trasversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        pathLength[startIndex] = 0;
        predecessor[startIndex] = -1;

        while (!trasversalQueue.isEmpty() && (index != targetIndex)) {
            index = trasversalQueue.dequeue();

            for (int i = 0; i < this.numVertices; i++) {
                if (this.adjMatrix[index][i] && !visited[i]) {
                    pathLength[i] = pathLength[index] + 1;
                    predecessor[i] = index;
                    trasversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }

        if (index != targetIndex) {
            return resultList.iterator();
        }

        LinkedStack<Integer> stack = new LinkedStack<>();
        index = targetIndex;
        stack.push(index);

        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear(stack.pop());
        }

        return resultList.iterator();
    }
}

