public class VariableScope {
  public static void main(String[] args) {
    int x1 = 0;
    for (int i = 0; i < 5; i++) { 
      // i and square are only available in this for loop
      int square = i*i;
      x1 += square;
      System.out.println("i = " + i + ", x1 = " + x1);
    }
    // System.out.println("i = " + i + " , square = " + square); // Cause error
    System.out.println("x1 = " + x1);
    if (x1 > 15) {
      int x2 = 2 * x1;
      System.out.println("x2 = " + x2);
    }
    // System.out.println("x2 = " + x2);  // Cause error
    System.out.println("x1 = " + x1);
  }
}