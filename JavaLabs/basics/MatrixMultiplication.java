import java.util.Random;

public class MatrixMultiplication {
  public static void main(String[] args) {
    int[][] a = new int[5][5];
    int[][] b = new int[5][5];
    int[][] c = new int[5][5];
    Random r = new Random(); // Create a Random object
    // r.setSeed(1);
    // Initialize arrays a and b with random integers, 0..9
    for (int i = 0; i < 5; i++)
      for (int j = 0; j < 5; j++) {
        a[i][j] = r.nextInt(10);
        b[i][j] = r.nextInt(10);
      }
    // Calculate C = A x B
    c = multiply(a, b);
    // Print arrays
    printMatrix(a, "Contents of matrix A:", "%5d");
    printMatrix(b, "Contents of matrix B:", "%5d");
    printMatrix(c, "Contents of matrix C:", "%5d");
  }
  
  static void printMatrix(int[][] x, String title, String format) {
    System.out.println(title);
    for (int i = 0; i < x.length; i++) {
      for (int j = 0; j < x[0].length; j++) 
        System.out.printf(format, x[i][j]);
      System.out.println();
    }  
  }
  
  // Calculate C = A x B
  static int[][] multiply(int[][] a, int[][] b) {
    // c[][] has the same size as a[][]
    int[][] c = new int[a.length][a[0].length];
    for (int i = 0; i < c.length; i++) 
      for (int j = 0; j < c[0].length; j++) {
        int sum = 0;
        for (int k = 0; k < c[0].length; k++)
          sum += a[i][k] * b[k][j];
        c[i][j] = sum;
      }
    return c;
  }
}