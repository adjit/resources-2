public class Arrays {
  public static void main(String[] args) {
    // 1-D arrays
    int[] a1 = new int[6];
    for (int i = 0; i <a1.length; i++)
      a1[i] = i;
    for (int i = 0; i < a1.length; i++)
      System.out.print("a1[" + i + "] = " + a1[i] + "  ");
    System.out.println(); // add "/n"
    int total = 0;
    for (int i = 0; i < a1.length; i++)
      total += a1[i];
    System.out.println("Array a1 has total value " + total);
    int[] a2 = {24, 12, 18, 45, 14, 5, 8};
    for (int i = 0; i < a2.length; i++)
      System.out.print("a2[" + i + "] = " + a2[i] + "  ");
    System.out.println(); // add "/n"
    // 2-D arrays
    int[][] a3 = new int[4][5];
    for (int i = 0; i < a3.length; i++) {
      for (int j = 0; j < a3[i].length; j++)
        a3[i][j] = i + j;
    }
    System.out.println("Contents of array a3:");
    for (int i = 0; i < a3.length; i++) {
      for (int j = 0; j < a3[i].length; j++)
        System.out.print(a3[i][j] + " ");
      System.out.println(); // Add a "/n"
    }
  }
}