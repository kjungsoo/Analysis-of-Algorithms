import edu.princeton.cs.algs4.StdOut;
//import java.math.*;

public class Windchill {
  private double t; //temperature
  private double v; //velocity
  
  public Windchill(double temperature, double velocity){
    this.t = temperature;
    this.v = velocity;
  }
  
  public double calcWC() {
    double windchill = 35.74 + .6215 * this.t + (.4275 * this.t - 35.75) * Math.pow(this.v, 0.16);
    return windchill;
  }
  
  public static void main(String[] args){
    Windchill calc_wc = new Windchill(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
    double calculation = calc_wc.calcWC();
    StdOut.println(calculation);
    //System.out.println(calculation);
  }
}