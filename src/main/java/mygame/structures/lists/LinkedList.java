package mygame.structures.lists;


import mygame.exceptions.ElementNotFoundException;
import mygame.exceptions.EmptyListException;

public abstract class LinkedList<T> {

    private int count;
    private LinearNode<T> head, tail;

    /**
     * Constructor of LinkedList
     */
    public LinkedList() {
        this.count = 0;
        this.head = this.tail = null;
    }

    /**
     * Getter for count
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Getter for node head
     *
     * @return the node head
     */
    public LinearNode<T> getHead() {
        return head;
    }

    /**
     * Getter for node tail
     *
     * @return the node tail
     */
    public LinearNode<T> getTail() {
        return tail;
    }

    /**
     * Metodo para adicionar um elemento ao node
     *
     * @param element
     */
    public void add(T element) {
        LinearNode<T> newElement = new LinearNode<>(element);

        /**
         * Percorre este if quando a lista está vazia. Tem que se apontar o head e o tail ao novo elemento.
         * Se caso fosse usado o setNext, o novo elemento estaria a apontar para um node nulo, o que é obviamente impossivel
         */
        if (this.head == null) {
            this.head = newElement;
            this.tail = newElement;
            // this.head = this.tail = element
        } else {
            /**
             * Se a lista tiver elementos entra no else. O novo elemento tem que ligar ao elemento anteriormente ligado ao head
             * e o head ao novo elemento a adicionar.
             */
            newElement.setNext(this.head);
            this.head = newElement;
        }
        count++;
    }

    private boolean isEmpty() {
        return (this.count == 0);
    }

    public void remove(T element) throws EmptyListException, ElementNotFoundException {
        // Caso a lista esteja vazia
        if (count == 0) {
            throw new EmptyListException("A lista esta vazia");
        } else {
            LinearNode current = this.head;
            LinearNode previous = null;
            boolean found = false;

            while (current != null && found == false) {
                if (current.getElement() == element) {
                    found = true;
                } else {
                    previous = current;
                    current = current.getNext();
                }
            }
            if (!found) {
                throw new ElementNotFoundException("O node nao foi encontrado");
            }
            if (current.equals(head)) {
                // Remove o ultimo node da lista
                this.head.setNext(current.getNext());
                this.head = current.getNext();
            } else if (current.equals(tail)) {
                // Remove o primeiro
                previous.setNext(tail.getNext());
                this.tail = previous;
            } else if (current.equals(tail)) {
                // Caso o primeiro node seja o unico elemento da lista
                this.head = null;
                this.tail = null;
            } else {
                // Remove um node do meio da lista
                previous.setNext(current.getNext());
            }
            count--; // Depois de removido decrementa-se o contador
        }
    }

    /**
     * Imprimir todos os elementos da list
     */
    public void printList() throws EmptyListException {
        LinearNode node = this.head;
        //só imprime se houver elementos na lista
        while (node != null) {
            System.out.print(node.toString() + "\n");
            node = node.getNext();
        }
    }
}
