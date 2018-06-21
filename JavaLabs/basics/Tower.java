public class Tower {
  public static void main(String[] args) {
    int n = 0;  // input integer
    try {
      n = Integer.parseInt(args[0]);
      if (n < 1)
        throw new Exception("Input value is not positive");
      if (n > 15)
        throw new Exception("Input value is too large");
    }
    catch (Exception e) {
      System.out.println(e);
      System.out.println("Usage: java  Tower  [positive-integer]");
      System.exit(-1);
    }
    long startTime = System.currentTimeMillis(); // record start time
    tower(n, 'A', 'C', 'B');
    long stopTime = System.currentTimeMillis();  // record stop time
    System.out.println("Problem is solved in "
                       + (stopTime - startTime) + " milliseconds");
  }
  
  /**
  * Move <code>n</code> disks from peg <code>fromPeg</code> to peg <code>toPeg</code>
  * with the help of peg <code>helpPeg</code>.
  * @param n number of disks
  * @param fromPeg starting peg
  * @param toPeg destination peg
  * @param helpPeg auxiliary peg
  */
  static void tower(int n, char fromPeg, char toPeg, char helpPeg) {
    if (n <= 0)
      return;
    if (n > 1)
      tower(n-1, fromPeg, helpPeg, toPeg);
    System.out.println("Move top disk from peg " + fromPeg + " to peg "
                       + toPeg);
    if (n > 1)
      tower(n-1, helpPeg, toPeg, fromPeg);
  }
}