public class LoopInterruption {
  public static void main(String args[]) {
    int i = 0;
    for (; i < 10; i++) {
      System.out.println("Enter loop body: i = " + i);
      if ((i % 2) == 0) continue;
      if (i == 7) break;
      System.out.println("Leave loop body: i = " + i);
    }
    System.out.println("After loop: i = " + i);
  }
}