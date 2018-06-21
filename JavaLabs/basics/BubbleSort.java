import java.util.Random;

public class BubbleSort {
  public static void main(String[] args) {
    int[] d = new int[10];
    Random r = new Random(); // create a Random object
    for (int i = 0; i  < d.length; i++) 
      d[i] = r.nextInt(50); //generate random numbers between 0 and 49
    System.out.print("Data before sorting: ");
    printArray(d);
    bubbleSort(d);
    System.out.print("Data after sorting:  ");
    printArray(d);
  }
  
  static void bubbleSort(int[] a) {
    for (int limit = a.length - 1; limit > 0; limit--) {
      for (int j = 0; j < limit; j++) {
        if (a[j] > a[j+1]) {
          // swap values in a[j] and a[j+1]
          int temp = a[j];
          a[j] = a[j+1];
          a[j+1] = temp;
        }
      }
    }
  }
  
  static void printArray(int[] a) {
    for (int i = 0; i < a.length; i++)
      System.out.print(a[i] + " ");
    System.out.println();
  }
}