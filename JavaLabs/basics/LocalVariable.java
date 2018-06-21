public class LocalVariable {
  public static void main(String[] args) {
    int x; 
    int y = 0;
    int z1, z2 = 1, z3 = 2;
    x = 1;
    System.out.println("x = " + x);
    x = x + 1;
    System.out.println("x = " + x);
    x++;
    System.out.println("x = " + x);
    ++x;
    System.out.println("x = " + x);
    x += 2;
    System.out.println("x = " + x);
    z1 = 1 + x++;
    System.out.println("x = " + x + ", z1 = " + z1);
    z1 = 1 + ++x;
    System.out.println("x = " + x + ", z1 = " + z1);
    y = z2 + z3;
    System.out.println("y = " + y);
    System.out.println("x = " + x + ", x-- = " + x--);
    System.out.println("x = " + x + ", --x = " + --x);
  }
}