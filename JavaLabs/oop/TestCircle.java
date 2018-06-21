public class TestCircle {
  public static void main(String[] args) {
    Circle x = new Circle(1.0);
    System.out.println(x);
    Circle y = new Circle();
    System.out.println(y);
    y.setRadius(2.0);
    System.out.println(y + " has area " + y.area());
  }
}

class Circle {
  private double radius;
  // deine PI as nickname for constant 3.14
  private final double PI = 3.14;  

  // Constructor 1
  public Circle(double radius) {
    System.out.println("Enter constructor Circle(" + radius + ")");    
    this.radius = radius; // this = the current object  
  } 

  // Constructor 2: the default constructor
  public Circle() {
    this(0.0); // call constructor 1; this = class name
  }
  
  // Setter method
  public void setRadius(double radius) {
    this.radius = radius;
  }

  // Getter method
  public double getRadius() {
    return radius;
  }

  // Calculate area of the circle
  public double area() {
    return PI * radius * radius;
  }

  // Define how to print Circle objects
  public String toString() {
    return "Circle(" + radius + ")";
  }
}
