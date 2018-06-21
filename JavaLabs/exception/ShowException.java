// Prof. Lixin Tao, Pace University, 2003
// To compile, run "javac ShowException.java"
// To run, run "java ShowException"

class Exception1 extends Exception {}
class Exception2 extends Exception {}
class Exception3 extends Exception {}

public class ShowException {
  public static void main(String[] args) {
    for (int i = 1; i<=3; i++) {
      try {
        f(i);
      }
      catch (Exception3 e) {
        System.out.println("main() catched " + e);
      }
    }
  }

  static void f(int i) throws Exception3 {
    try {
      if (i == 1)
        throw new Exception1();
      else if (i == 2)
        throw new Exception2();
      else if (i == 3)
        throw new Exception3();
    }
    catch (Exception1 e) {
      System.out.println("f(" + i + ") catched " + e);
    }
    catch (Exception2 e) {
      System.out.println("f(" + i + ") catched " + e);
      throw new Exception3();
    }
    finally {
      System.out.println("f(" + i + ") executes finally block");
    }
  }
}