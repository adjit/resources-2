import java.util.*;

public class BinarySearch {
  public static void main(String[] args) {
    int[] d = new int[10];
    Random r = new Random(); // create a Random object
    for (int i = 0; i  < d.length; i++) 
      d[i] = r.nextInt(50); //generate random numbers between 0 and 49
    BubbleSort.bubbleSort(d);
    System.out.print("Data to be searched into: ");
    BubbleSort.printArray(d);
    System.out.print("Please enter an integer to search for: ");
    Scanner keyboard = new Scanner(System.in); // creat a Scanner object for keyboard
    int v = keyboard.nextInt();
    int index1 = binarySearch(d, v);
    int index2 = java.util.Arrays.binarySearch(d, v); // search by library method
    if (index1 == -1) {
      System.out.println("Value " + v + " is not in array d[]");
      System.exit(0);
    }
    System.out.println("Our implementation:     Value " 
                       + v + " is in d[" + index1 + "]");
    System.out.println("Library implementation: Value " 
                       + v + " is in d[" + index2 + "]");
  }
  
  /*
  * Returns the index of key in array a[] if key is in a[],
  * -1 otherwise.
  * @param a the array of data to be searched in.
  * @param key the value to be searched for
  */
  static int binarySearch(int[] a, int key) {
    int leftLimit = 0;
    int rightLimit = a.length - 1;
    while (leftLimit <= rightLimit) {
      int middle = (leftLimit + rightLimit)/2;
      if (a[middle] == key)
        return middle;
      if (a[middle] < key)  
        leftLimit = middle + 1; // key can only be in a[middle+1..rightLimit]
      else //a[middle] > key
        rightLimit = middle -1; // key can only be in a[leftLimit..middle-1]
    }
    return -1;
  }
}