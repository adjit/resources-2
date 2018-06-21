/**
* Class <code>JavaDocDemo</code> shows example method
* declarations and invocations.
* @author Dr. Lixin Tao
* @version 1.0
*/
public class JavaDocDemo {
  /**
  * Method <code>main()</code> is the entrance method to be executed.
  * @param args the array of command line arguments.
  */
  public static void main(String[] args) {
    int x = 0;
    x = square(2);
    print(x);
    x = max(x, 1, 5);
    print(x);
    print(min(5, 2, 8));
  }
  
  /** 
  * Returns the squared value of parameter x.
  * @param x the value to be squared.
  * @return the squared value of parameter x.
  */
  static int square(int x) {
    return x*x;
  }
  
  /**
  * Finds the maximum of the input values.
  * @param x1 the first input value.
  * @param x2 the second input value.
  * @param x3 the third input value.
  * @return the maximum of the input values.
  */
  static int max(int x1, int x2, int x3) {
    if ((x1 > x2) && (x1 > x3))
      return x1;
    else if (x2 > x3)
      return x2;
    else 
      return x3;
  }
  
  /**
  * Finds the minimum of the input values.
  * @param x1 the first input value.
  * @param x2 the second input value.
  * @param x3 the third input value.
  * @return the minimum of the input values.
  */
  static int min(int x1, int x2, int x3) {
    int smaller = ((x1 < x2) ? x1 : x2);
    return smaller < x3  ? smaller : x3;
  }
  
  /**
  * Prints input value in a special format.
  * @param x value to be printed.
  */
  static void print(int x) {
    System.out.println("Output: " + x);
  }
}