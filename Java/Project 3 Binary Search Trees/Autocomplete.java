import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Comparator;

// A data type that provides autocomplete functionality for a given set of 
// string and weights, using Term and BinarySearchDeluxe. 
public class Autocomplete {
    private Term[] listOfMatchingWords;
    
    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) throw new NullPointerException();
        listOfMatchingWords = terms;
    }
    
    // All terms that start with the given prefix, in descending order of
    // weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) throw new NullPointerException();
        String containsPrefix = prefix;
        Term[] matchingWordsMax = new Term[listOfMatchingWords.length];
        int index = 0;
        
        for (Term word : listOfMatchingWords) {
            if (word.queryterm.contains(containsPrefix)) {
                matchingWordsMax[index] = word;
                index++;
            }
        }
        Term[] matchWordsExact = new Term[index - 1];
        for (int i = 0; i < matchWordsExact.length; i++) {
            matchWordsExact[i] = matchingWordsMax[i];
        }
        return matchWordsExact;
    }
    
    // The number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) throw new NullPointerException();
        String containsPrefix = prefix;
        Term[] matchingWordsMax = new Term[listOfMatchingWords.length];
        int index = 0;
        
        for (Term word : listOfMatchingWords) {
            if (word.queryterm.contains(containsPrefix)) {
                matchingWordsMax[index] = word;
                index++;
            }
        }
        Term[] matchWordsExact = new Term[index - 1];
        for (int i = 0; i < matchWordsExact.length; i++) {
            matchWordsExact[i] = matchingWordsMax[i];
        }
        return matchWordsExact.length;
    }
    
    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong(); 
            in.readChar(); 
            String query = in.readLine(); 
            terms[i] = new Term(query.trim(), weight); 
        }
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println(results[i]);
            }
        }
    }
}
