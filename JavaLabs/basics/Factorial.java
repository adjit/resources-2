public class Factorial {
  public static void main(String[] args) {
    int n = 0;  // input integer
    try {
      n = Integer.parseInt(args[0]);
      if (n < 0)
        throw new Exception("Input value is negative");
      if (n > 20)
        throw new Exception("Input value is too large");
    }
    catch (Exception e) {
      System.out.println(e);
      System.out.println("Usage: java  Factorial  [non-negative-integer]");
      System.exit(-1);
    }
    long answer = 0;
    answer = recursiveFactorial(n);
    System.out.println("Recursive Factorial gets answer " + answer);
    answer = iterativeFactorial(n);
    System.out.println("Iterative Factorial gets answer " + answer);
  }
  
  static long recursiveFactorial(long n) {
    if (n == 0)
      return 1;  // 0! = 1
    else
      return n*recursiveFactorial(n-1);
  }
  
  static long iterativeFactorial(int n) {
    long answer = 1;
    for (int i = 1; i <= n; i++)
      answer *= i;
    return answer;
  }
}