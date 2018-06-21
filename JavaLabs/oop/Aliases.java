public class Aliases {
  public static void main(String[] args) {
    Circle x = new Circle(2);
    Circle y = x;
    System.out.println("x: " + x);
    System.out.println("y: " + y);
    y.setRadius(4);
    System.out.println("x: " + x);
    System.out.println("y: " + y);
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