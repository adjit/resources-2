public class MethodOverloading {
  public static void main(String[] args) {
    Utility u = new Utility();
    u.print();
    u.print(5);
    u.print(5.0);
    u.print(2, 4);
  }
}

class Utility {
  public void print() {
    System.out.println("null");
  }

  public void print(int i) {
    System.out.println("Integer " + i);
  }
  
  public void print(double d) {
    System.out.println("Double " + d);
  }
  
  public void print(int i, int j) {
    System.out.println("Integers " + i + " and " + j);
  }
}