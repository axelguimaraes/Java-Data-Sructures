package mygame.structures.classes;

public class EncryptWithCircularArrayQueue {

    private final CircularArrayQueue<Integer> circulcarArrayQueue;

    public EncryptWithCircularArrayQueue(int[] key) {
        this.circulcarArrayQueue = new CircularArrayQueue<>(key.length);
        this.cloneKeyToCircularArrayQueue(key);
    }

    private void cloneKeyToCircularArrayQueue(int[] key) {
        for (int i = 0; i < key.length; i++) {
            this.circulcarArrayQueue.enqueue(key[i]);
        }
    }

    public String encrypt(String toEncrypt) {

        char[] chars = new char[toEncrypt.length()];

        for (int i = 0; i < toEncrypt.length(); i++) {
            int dequeue = this.circulcarArrayQueue.dequeue();
            chars[i] += (char) toEncrypt.charAt(i) + dequeue;

            this.circulcarArrayQueue.enqueue(dequeue);
        }
        return String.valueOf(chars);
    }
}
