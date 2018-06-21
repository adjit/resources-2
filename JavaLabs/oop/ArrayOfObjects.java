public class ArrayOfObjects {
  public static void main(String[] args) {
    Circle[] x = new Circle[5];  // create array of 5 Circle reference variables
    for (int i = 0; i < x.length; i++)
      x[i] = new Circle(i); // create Circle objects
    printArray("x[]: ", x);
    
    // making a copy of array of objects in c; array c[] and x[] are separate
    Circle[] c = new Circle[x.length];
    for (int i = 0; i < x.length; i++)
      c[i] = x[i].clone();
      
    Circle[] y = x;    // y is an alias of x; they refer to the same array.
    y[0].setRadius(6);
    System.out.println("After setting y[0] = 6");
    printArray("x[]: ", x);
    printArray("y[]: ", y);
    printArray("c[]: ", c);
  }
  
  static void printArray(String title, Circle[] a) {
  System.out.print(title);
    for (int i = 0; i < a.length; i++)
      System.out.print(a[i] + " ");
    System.out.println();
  }
}

class Circle implements Cloneable {
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
  
  public Circle clone() {
    Circle c = null;
    try {
      c = (Circle)super.clone();
      c.radius = radius;  // not necessary; done in super.clone() already
    }
    catch(Exception e){}
    return c;
  }
}