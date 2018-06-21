public class Method {
  public static void main(String[] args) {
    int x = 0;
    x = square(2);
    print(x);
    x = max(x, 1, 5);
    print(x);
    print(min(5, 2, 8));
  }
  
  static int square(int x) {
    return x*x;
  }
  
  static int max(int x1, int x2, int x3) {
    if ((x1 > x2) && (x1 > x3))
      return x1;
    else if (x2 > x3)
      return x2;
    else 
      return x3;
  }
  
  static int min(int x1, int x2, int x3) {
    int smaller = ((x1 < x2) ? x1 : x2);
    return smaller < x3  ? smaller : x3;
  }
  
  static void print(int x) {
    System.out.println("Output: " + x);
  }
}