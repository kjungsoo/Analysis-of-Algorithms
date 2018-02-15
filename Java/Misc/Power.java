import edu.princeton.cs.algs4.StdOut;

public class Power {
  private static int power(int a, int b){
    return (b == 0) ? 1: (b == 1) ? a : a * power(a, b - 1);
  }
  
  private static int power2(int a, int b) {
    if (b == 0) return 1;
    return (b % 2 == 0) ? power(a * a, b/2): a * power(a, b - 1);
  }
  
  public static void main(String[] args){
    int a = Integer.parseInt(args[0]);
    int b = Integer.parseInt(args[1]);
    StdOut.println(power(a,b));
    //System.out.println(power2(a,b));
  }
}