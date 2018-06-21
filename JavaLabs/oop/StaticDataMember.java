public class StaticDataMember {
  public static void main(String[] args) {
    Circle x = new Circle(2);
    System.out.println("x: " + x);
    Circle y = new Circle(3);
    System.out.println("x: " + x);
    System.out.println("y: " + y);
    System.out.println("x.nbrOfCircles: " + x.nbrOfCircles);
    System.out.println("y.nbrOfCircles: " + y.nbrOfCircles);
    System.out.println("Circle.nbrOfCircles: " + Circle.nbrOfCircles);
  }
}

class Circle {
  private int radius = 0;
  static int nbrOfCircles = 0;
  
  public Circle(int radius) {
    this.radius = radius;
    nbrOfCircles++;
  }
  
  public int getRadius() {
    return radius;
  }
  
  public String toString() {
    return "Circle(" + radius + ":" + nbrOfCircles + ")";
  }
}