//import edu.princeton.cs.algs4.StdArrayIO;
//import edu.princeton.cs.algs4.StdOut;

public class ManhattanDistance{
  private static double distance(double[] x, double[] y){
    double[] x_coordinate = x;
    double[] y_coordiante = y;
    double[] manh_distance = Math.abs(x_coordinate[0] - x_coordinate[1]) + Math.abs(y_coordinate[0] - y_coordinate[1]);
    return manh_distance;
  }
  
  public static void main(String[] args){
    double[] x = StdArrayIO.readDouble1D();
    double[] y = StdArrayIO.readDouble1D();
    StdOut.println(distance(x, y));
  }
}

//sqrt((x1 - x2) * (y1 - y2))

//