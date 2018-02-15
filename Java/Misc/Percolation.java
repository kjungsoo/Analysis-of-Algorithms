import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;
//import java.util.Random;

// Models an N-by-N percolation system.
public class Percolation {
	
	private int grid_width_x;
	private int grid_height_y;
	
	private boolean[] grid_sites;
	//private int[] grid_temp;
	
	private WeightedQuickUnionUF qUF;
	private int top = 0;
	private int bottom;
	
    public Percolation(int N) { //creat n x n grid, all sites blocked
        grid_height_y = grid_width_x = N + 1;
		//0 == blocked; 1 == open; 2 == full
		grid_sites = new boolean[grid_height_y][grid_width_x];
		//this grid_temp = new int[grid_width_x][grid_height_y];
		
		/*for (int row = 0; row < grid_height_y; row++) {
			for (int column = 0; column < grid_width_x; column++) {
				grid_sites[column][row] = -1;
				grid_temp[column][row] = -1;
			}
		} */
		qUF = new WeightedQuickUnionUF( grid_width_x * grid_height_y + 2);
		bottom = grid_width_x * grid_height_y + 1;
    }

    // Open site (row, col) if it is not open already.
    public void open(int row, int col) {
		if (row <= 0 || col <= 0) throw new java.lang.IllegalArgumentException(Integer.toString(row) + Integer.toString(col));
        else {
			if (!isOpen(row, col)) grid_sites[row][col] = true;
		}
		
		int index = (row - 1)*(grid_width_x - 1) + col;
		
		if (row == 1) qUF.union(index, top);
		if (row == grid_height_y) qUF.union(index, bottom);
		//connect neighbor with union;
		if (row > 1 && isOpen(row - 1, col)) {
			int neighbor_index = (row - 2) * (grid_width_x - 1) + col;
			qUF.union(index, neighbor_index);
		} 
		if (row < grid_height_y && isOpen(row + 1, col)) {
			int neighbor_index = (row) * (grid_width_x - 1) + col;
			qUF.union(index, neighbor_index);
		} 
		if (row < grid_width_x && isOpen(row, col + 1)) {
			int neighbor_index = (row - 1) * (grid_width_x - 1) + (col - 1);
			qUF.union(index, neighbor_index);
		}
		if (row > 1 isOpen(row, col - 1)) {
			int neighbor_index = (row - 1) * (grid_width_x - 1) + col + 1;
			qUF.union(index, neighbor_index);
		}
		
    }

    // Is site (row, col) open?
    public boolean isOpen(int row, int col) { return grid_sites[row][col]; }

    // Is site (row, col) full?
    public boolean isFull(int row, int col) {
		if (row > 0 && col > 0 && row <= grid_height_y && col <= grid_width_x) return qUF.connected(top, ((row - 1) * (grid_width_x - 1) + col));
	} else { return false }

    // Number of open sites.
    public int numberOfOpenSites() {
        int counter = 0;
		for (int row = 0; row < grid_height_y; row++) {
			for (int column = 0; column < grid_width_x; column++) {
				if (grid_sites[row][column]) counter++;
			}
		}
		return counter;
    }

    // Does the system percolate?
    public boolean percolates() {
        /*
		for (int row = 0; row < grid_height_y; row++) {
			for (int column = 0; column < grid_width_x; column++) {
				
			}
		} */
		return qUF.connected(top, bottom);
    }

    // An integer ID (1...N) for site (row, col).
    private int encode(int row, int col) {
        return int index = (row - 1)*(grid_width_x - 1) + col;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Percolation perc = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.println(perc.numberOfOpenSites() + " open sites");
        if (perc.percolates()) {
            StdOut.println("percolates");
        }
        else {
            StdOut.println("does not percolate");
        }
        
        // Check if site (i, j) optionally specified on the command line
        // is full.
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.println(perc.isFull(i, j));
        }
    }
}
