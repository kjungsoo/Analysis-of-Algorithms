import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Random queue implementation using a resizing array.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;
    private int first;
    private int last;
    
    // Construct an empty queue.
    public ResizingArrayRandomQueue() {
        queue = (Item[]) new Object[2];
        size = first = last = 0;
    }
    
    // Is the queue empty?
    public boolean isEmpty() { return size == 0; }
    
    // The number of items on the queue.
    public int size() { return size; }
    
    // Add item to the queue.
    public void enqueue(Item item) {
        if (item == null) throw new NoSuchElementException();
        if (size == queue.length) resize(2 * queue.length);
        queue[last++] = item;
        size++;
    }
    
    // Remove and return a random item from the queue.
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int randIndex = StdRandom.uniform(0,size);
        Item item = queue[randIndex];
        queue[randIndex] = null;
        size--;
        if (size > 0 && size == queue.length/4) resize (queue.length/2);
        return item;
    }
    
    // Return a random item from the queue, but do not remove it.
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int randIndex = StdRandom.uniform(0,size);
        return queue[randIndex];
    }
    
    // An independent iterator over items in the queue in random order.
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }
    
    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        private int randInt;
        
        
        RandomQueueIterator() {
            randInt = StdRandom.uniform(0,size);
        }
        
        public boolean hasNext()  { return size == 0; }
        
        public void remove() { throw new UnsupportedOperationException(); }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return queue[randInt];
        }
    }
    
    // A string representation of the queue.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }
    
    // Helper method for resizing the underlying array.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < size; i++) {
            if (queue[i] != null) {
                temp[i] = queue[i];
            }
        }
        queue = temp;
        first = 0;
        last = size;
    }
    
    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q = 
            new ResizingArrayRandomQueue<Integer>();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readInt());
        }
        int sum1 = 0;
        for (int x : q) {
            sum1 += x;
        }
        int sum2 = sum1;
        for (int x : q) {
            sum2 -= x;
        }
        int sum3 = 0;
        while (q.size() > 0) {
            sum3 += q.dequeue();
        }
        StdOut.println(sum1);
        StdOut.println(sum2);
        StdOut.println(sum3);
        StdOut.println(q.isEmpty());
    }
}
