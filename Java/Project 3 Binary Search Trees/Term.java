import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Comparator;
import static java.lang.Math.toIntExact;

// An immutable data type that represents an autocomplete term: a query string 
// and an associated real-valued weight.
public class Term implements Comparable<Term> {
    public String queryterm;
    public long weight;
    
    // Construct a term given the associated query string, having weight 0.
    public Term(String query) {
        if (query == null) throw new NullPointerException();
        queryterm = query;
        weight = 0;
    }
    
    // Construct a term given the associated query string and weight.
    public Term(String query, long weight) {
        if (query == null) throw new NullPointerException();
        if (weight < 0) throw new IllegalArgumentException();
        queryterm = query;
        this.weight = weight;
    }
    
    // A reverse-weight comparator.
    public static Comparator<Term> byReverseWeightOrder() { return new ReverseWeightOrder(); } //?
    
    // Helper reverse-weight comparator.
    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term v, Term w) {
            return toIntExact(w.weight - v.weight);
        }
    }
    
    // A prefix-order comparator.
    public static Comparator<Term> byPrefixOrder(int r) { 
        if (r < 0) throw new IllegalArgumentException();
        return new PrefixOrder(r); //?
    }
    
    // Helper prefix-order comparator.
    private static class PrefixOrder implements Comparator<Term> {
        private int substrlen;
        
        PrefixOrder(int r) {
            substrlen = r;
        }
        
        public int compare(Term v, Term w) {
            String subV = v.queryterm.substring(0,substrlen);
            String subW = w.queryterm.substring(0,substrlen);
            return subV.compareTo(subW);
        }
    }
    
    // Compare this term to that in lexicographic order by query and 
    // return a negative, zero, or positive integer based on whether this 
    // term is smaller, equal to, or larger than that term.
    public int compareTo(Term that) {
        int cmpVal = this.queryterm.compareTo(that.queryterm);
        if (cmpVal > 0) { return -1; }
        if (cmpVal < 0) { return 1; }
        return cmpVal;
    }
    
    // A string representation of this term.
    public String toString() {
        return weight + "\t" + queryterm;
    }
    
    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong(); 
            in.readChar(); 
            String query = in.readLine(); 
            terms[i] = new Term(query.trim(), weight); 
        }
        StdOut.printf("Top %d by lexicographic order:\n", k);
        Arrays.sort(terms);
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
        StdOut.printf("Top %d by reverse-weight order:\n", k);
        Arrays.sort(terms, Term.byReverseWeightOrder());
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
    }
}
