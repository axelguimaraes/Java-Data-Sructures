package mygame.structures.classes;

public class EncryptWithLinkedQueue {

    private final LinkedQueue<Integer> linkedQueue;

    public EncryptWithLinkedQueue(int[] key) {
        this.linkedQueue = new LinkedQueue<>();
        this.cloneKeyToCircularArrayQueue(key);
    }

    private void cloneKeyToCircularArrayQueue(int[] key) {
        for (int i = 0; i < key.length; i++) {
            this.linkedQueue.enqueue(key[i]);
        }
    }

    public String encrypt(String toEncrypt) {

        char[] chars = new char[toEncrypt.length()];

        for (int i = 0; i < toEncrypt.length(); i++) {
            int dequeue = this.linkedQueue.dequeue();
            chars[i] += (char) toEncrypt.charAt(i) + dequeue;

            this.linkedQueue.enqueue(dequeue);
        }
        return String.valueOf(chars);
    }
}
