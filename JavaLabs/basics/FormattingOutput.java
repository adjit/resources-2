import java.util.Random;

public class FormattingOutput {
  public static void main(String[] args) {
    int[][] a = new int[5][5];
    double[][] d = new double[5][5];
    Random r = new Random(); // Create a Random object
    for (int i = 0; i < 5; i++) 
      for (int j = 0; j < 5; j++) {
        a[i][j] = r.nextInt(10);
        d[i][j] = r.nextDouble()*10.0;
      }
    System.out.println("Contents of matrix A:");
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) 
        System.out.printf("%6d", a[i][j]);
      System.out.println();
    }
    System.out.println("Contents of matrix D:");
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) 
        System.out.printf("%6.2f", d[i][j]);
      System.out.println();
    }
    double x = d[0][0];
    System.out.println("Without formatting, x = " + x);
    System.out.printf("With formatting, x = %4.2f", x);
  }
}