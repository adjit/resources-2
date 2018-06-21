public class FindingPi {
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis(); // record start time
    double current = 1.0; // current approximate value of Pi/4
    double last = 0.0;    // last approximate value of Pi/4
    double sign = 1.0;    // last term's sign
    double denominator = 1.0;  // last term's denominator
    double tolerance = 0.000000001; // allowed tolerance
    while (Math.abs(current - last) > tolerance) {
      sign *= -1.0;         // negate the sign for the sign of the next term
      denominator += 2.0;   // get the denominator for the next term
      last = current;       // current approximate becomes the last one
      current += sign/denominator; // next approximate becomes the current one
    }
    System.out.printf("Pi = %9.7f\n", 4.0*current);
    long stopTime = System.currentTimeMillis();  // record stop time
    System.out.println("This program has spent " +
                       (stopTime-startTime) + " msecs running");
  }
}