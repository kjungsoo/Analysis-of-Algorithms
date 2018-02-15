import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import Math;

// Estimates percolation threshold for an N-by-N percolation system.
public class PercolationStats {
    private int totaltrials;
	private Percolation experiment;
	private double prThreshold;
	private double[] prResults;
	
    // Perform T independent experiments (Monte Carlo simulations) on an 
    // N-by-N grid.
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) 
			throw new java.lang.IllegalArgumentException(Integer.toString(N) + "" + Integer.toString(T));
		totaltrials = T;
		prResults = new double[T];
		for (int trial = 0; trial < totaltrials; trial++) {
			experiment = new Percolation(N);
			int siteCounter = 0;
			while(!experiment.percolates()) {
				int row = uniform(1, N + 1);
				int col = uniform(1, N + 1);
				if(!experiment.isOpen(row, col)) {
					experiment.open(row, col);
					siteCounter++;
				}
			}
			prThreshold = (double) siteCounter / (N * N);
			prResults[trial] = prThreshold;
		}
    }
    
    // Sample mean of percolation threshold.
    public double mean() {
        return mean(prResults);
    }

    // Sample standard deviation of percolation threshold.
    public double stddev() {
        return stddev(prResults);
    }

    // Low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        //return mean() - ((1.96 * stddev()) / Math.sqrt(experimentsCount));
		return mean() - ((1.96 * stddev()) / sqrt(totaltrials));
    }

    // High endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / sqrt(totaltrials));
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.printf("mean           = %f\n", stats.mean());
        StdOut.printf("stddev         = %f\n", stats.stddev());
        StdOut.printf("confidenceLow  = %f\n", stats.confidenceLow());
        StdOut.printf("confidenceHigh = %f\n", stats.confidenceHigh());
    }
}
