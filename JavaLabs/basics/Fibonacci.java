public class Fibonacci {
  public static void main(String[] args) {
    int n = 0;  // input integer
    try {
      n = Integer.parseInt(args[0]);
      if (n < 0)
        throw new Exception("Input value is negative");
      if (n > 50)
        throw new Exception("Input value is too large");
    }
    catch (Exception e) {
      System.out.println(e);
      System.out.println("Usage: java  Fibonacci  [non-negative-integer]");
      System.exit(-1);
    }
    long answer = 0;
    long startTime = System.currentTimeMillis(); // record start time
    answer = recursiveFibonacci(n);
    long stopTime = System.currentTimeMillis();  // record stop time
    System.out.println("Recursive Fibonacci gets answer " + answer
                       + " in " + (stopTime - startTime) + " milliseconds");
    startTime = System.currentTimeMillis(); // record start time
    answer = iterativeFibonacci(n);
    stopTime = System.currentTimeMillis();  // record stop time
    System.out.println("Iterative Fibonacci gets answer " + answer
                       + " in " + (stopTime - startTime) + " milliseconds");
  }
  
  static long recursiveFibonacci(long n) {
    if (n == 0 || n == 1)
      return n;  // fib(0) = 0, fib(1) = 1
    else
      return recursiveFibonacci(n-2) + recursiveFibonacci(n-1);
  }
  
  static long iterativeFibonacci(int n) {
    if (n == 0 || n == 1)
      return n;  // fib(0) = 0, fib(1) = 1
    long previous = 0;  // fib(0)
    long current = 1;   // fib(1)
    long next = 0;      // fib(2), fib(3), ... fib(n)
    for (int i = 2; i <= n; i++) {
      // evaluate next = fib(i), and make previous = old-current, current = next
      next = previous + current;
      previous = current;
      current = next;
    }
    return next;
  }
}