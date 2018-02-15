import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Takes a command-line integer k; reads in a sequence of strings from 
// standard input; and prints out exactly k of them, uniformly at random. 
// Note that each item from the sequence is printed out at most once.
public class Subset {
    public static void main(String[] args) {
        Integer amount = Integer.parseInt(args[0]);
        ResizingArrayRandomQueue<String> subset = new ResizingArrayRandomQueue<String>();
        while (!StdIn.isEmpty()) {
            subset.enqueue(StdIn.readString());
        }
        for (int i = 0; i < amount; i++) {
            StdOut.println(subset.dequeue());
        }
    }
}
