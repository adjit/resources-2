public class ObjectParameterPassing {
  public static void main(String[] args) {
    Circle x = new Circle(2);
    int n = 3;
    System.out.println("x: " + x);
    System.out.println("n: " + n);
    printCircle(x, n);
    System.out.println("x: " + x);
    System.out.println("n: " + n);
  }
  
  static void printCircle(Circle c, int times) {
    for (int i = 0; i < times; i++) 
      System.out.println("  " + c);
    c.setRadius(4);
    times = 5;
    System.out.println("  c: " + c);
    System.out.println("  times: " + times);
  }
}

class Circle {
  private int radius = 0;
  
  public Circle(int radius) {
    this.radius = radius;
  }
  
  public void setRadius(int r) {
    radius = r;
  }
  
  public String toString() {
    return "Circle(" + radius + ")";
  }
}